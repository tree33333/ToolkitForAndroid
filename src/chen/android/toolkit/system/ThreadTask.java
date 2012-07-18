package chen.android.toolkit.system;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;
import chen.android.toolkit.network.NetworkUtil;



/**
 * </br><b>name : </b>		ThreadTask
 * </br><b>description :</b>启动一个线程任务，并显示一个进度窗口。进度窗口不可关闭。使用此工具，可以避免主线线程阻塞出现ANR情况。
 * </br>@author : 			桥下一粒砂
 * </br><b>e-mail : </b>	chenyoca@gmail.com
 * </br><b>weibo : </b>		@桥下一粒砂
 * </br><b>date : </b>		2012-7-18 下午7:48:39
 *
 */
public class ThreadTask {

    /**
     * @ClassName: Status 
     * @Description: 状态
     * @author yongjia.chen
     * @date 2012-7-18 下午5:08:07
     *
     */
    public interface Status{
        /**
         * 加载完成
         */
        int FINISH = 0x00000000;
        
        /**
         * 网络不可用
         */
        int NETWORK_NOT_AVAILABLE = 0x00000001;
        
    };
    
    private static final String LOADING_TITLE = "正在加载数据，请稍候...";
    
    private static final String LOADING_MESSAGE = "根据您的网络状态，花费时间可能不同，请耐心等候...";

    private static void handle(boolean useNetwork,final Activity context,final Handler callback,final Runnable targetThread){
        //先检测网络
        if(useNetwork && !NetworkUtil.isAvailable(context)){
            callback.sendEmptyMessage(Status.NETWORK_NOT_AVAILABLE);
            return;
        }
        //创建提示窗口
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setTitle(LOADING_TITLE);
        dialog.setMessage(LOADING_MESSAGE);
        
        //任务线程
        Thread taskThread = new Thread(new Runnable() {
            @Override
            public void run() {
                targetThread.run();
                callback.sendEmptyMessage(Status.FINISH);
                dialog.cancel();
            }
        });
        
        //显示窗口
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.show();
            }
        });
        
        //启动线程
        taskThread.start();
    }
    
    /**
     * @Title: handleWithoutNetwork
     * @Description: TODO
     * @param context
     * @param callback
     * @param targetThread
     */
    public static void handleWithoutNetwork(final Activity context,final Handler callback,final Runnable targetThread){
        handle(false,context,callback,targetThread);
    }
    
    /**
     * @Title: handle
     * @Description: 异步方法。在一个线程里运行，执行状态在callback里回调
     * @param activity
     * @param callback
     */
    public static void handleWithNetwork(final Activity context,final Handler callback,final Runnable targetThread){
        handle(true,context,callback,targetThread);
    }
}
