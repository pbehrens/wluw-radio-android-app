//package com.wluw.stream;
//
//import java.util.Calendar;
//
//import android.os.Handler;
//import android.os.Message;
//
// 
//public class UpdateTimer extends Handler
//{
//    public final static int MSG_START = 0;
//    public final static int MSG_STOP = 1;
//    public final static int MSG_UPDATE = 2;
//    public final static int REFRESH_PERIOD = 1000; // in ms
//    public final static int SPIN_PERIOD = 100; // in ms 
// 
//    // pointer to the user interface adapter
//    //private DebugUIAdapter mUI;
//    // remember the last time the UI was updated
//    private long mLastTime;
// 
//    public UpdateTimer(DebugUIAdapter theUI)
//    {
//        super();
//       // mUI = theUI;
//        mLastTime = 0;
//    }
// 
//    // handle messages to implement the screen refresh timer
//    @Override
//    public void handleMessage(Message msg)
//    {
////        super.handleMessage(msg);
//// 
////        switch (msg.what)
////        {
////        case MSG_START:
////
////            this.sendEmptyMessage(MSG_UPDATE);
////            break;
//// 
////        case MSG_UPDATE:
////            this.checkTime();
////            this.sendEmptyMessageDelayed(MSG_UPDATE, SPIN_PERIOD);
////            break;                                 
//// 
////        case MSG_STOP:
////
////            this.removeMessages(MSG_UPDATE);
////            break;
//// 
////        default:
////            break;
//        }
//
//    private void checkTime()
//    {
//        long currTime = Calendar.getInstance().getTimeInMillis();
//        long dt = currTime - mLastTime;
//        if (dt > REFRESH_PERIOD)
//        {
//        	//mUI.updateDisplay();
//        	mLastTime = currTime;
//        }
//    }
//}