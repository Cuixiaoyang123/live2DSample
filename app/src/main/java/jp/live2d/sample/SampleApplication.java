/**
 *  You can modify and use this source freely
 *  only for the development of application related Live2D.
 *
 *  (c) Live2D Inc. All rights reserved.
 */

package jp.live2d.sample;

import jp.live2d.utils.android.FileManager;
import jp.live2d.utils.android.SoundManager;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;


public class SampleApplication extends Activity
{
	
	private LAppLive2DManager live2DMgr ;
	static private Activity instance;

	public SampleApplication( )
	{
		instance=this;
		if(LAppDefine.DEBUG_LOG)
		{
			Log.d( "", "==============================================\n" ) ;
			Log.d( "", "   Live2D Sample  \n" ) ;
			Log.d( "", "==============================================\n" ) ;
		}

		SoundManager.init(this);
		live2DMgr = new LAppLive2DManager(this) ;
	}


	 static public void exit()
    {
		SoundManager.release();
    	instance.finish();
    }


	
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        
      	setupGUI();
      	FileManager.init(this.getApplicationContext());
    }


	
	void setupGUI()
	{
    	setContentView(R.layout.activity_main);

        
        LAppView view = live2DMgr.createView(this) ;

        
        FrameLayout layout=(FrameLayout) findViewById(R.id.live2DLayout);
		layout.addView(view, 0, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));



		ClickListener listener = new ClickListener();
		ImageButton iBtn = (ImageButton)findViewById(R.id.imageButton1);
		iBtn.setOnClickListener(listener);
		ImageButton btn_hello = (ImageButton) findViewById(R.id.btn_hello);
		btn_hello.setOnClickListener(listener);
		ImageButton btn_shame = (ImageButton) findViewById(R.id.btn_shame);
		btn_shame.setOnClickListener(listener);
		ImageButton btn_sad = (ImageButton) findViewById(R.id.btn_sad);
		btn_sad.setOnClickListener(listener);
	}


	
	class ClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.imageButton1:
					Toast.makeText(getApplicationContext(), "change model", Toast.LENGTH_SHORT).show();
					live2DMgr.changeModel();//Live2D Event
					break;
				case R.id.btn_hello:
					Toast.makeText(getApplicationContext(), "say hello", Toast.LENGTH_SHORT).show();
					live2DMgr.shakeEvent();
//					live2DMgr.maxScaleEvent();
//					live2DMgr.minScaleEvent();
//					live2DMgr.showself();
					break;

				case R.id.btn_shame:
					live2DMgr.maxScaleEvent();
					break;
				case R.id.btn_sad:
					live2DMgr.oneByOneExpression();
					break;

				default:
					break;
			}
		}
	}


	
	@Override
	protected void onResume()
	{
		//live2DMgr.onResume() ;
		super.onResume();
	}


	
	@Override
	protected void onPause()
	{
		live2DMgr.onPause() ;
    	super.onPause();
	}
}
