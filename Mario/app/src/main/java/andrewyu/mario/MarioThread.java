package andrewyu.mario;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/*
 * Created by Andrew Yu on 5/17/2015.
 */
public class MarioThread extends Thread{
    private final MarioSurfaceView view;
    private static final int FRAME_PERIOD = 50; // In ms
    public MarioThread ( MarioSurfaceView view ) {
        this . view = view ;
    }
    public void run () {
        SurfaceHolder sh = view . getHolder () ;

// Main game loop .
        while ( ! Thread . interrupted () ) {
            Canvas c = sh . lockCanvas ( null ) ;
            try {
                synchronized ( sh ) {
                    view . renderGame ( c ) ;

                }
            } catch ( Exception e ) {
            } finally {
                if ( c != null ) {
                    sh . unlockCanvasAndPost ( c ) ;
                }
            }
            try {
                Thread . sleep ( FRAME_PERIOD ) ;
            } catch ( InterruptedException e ) {
                return ;
            }
        }
    }
}
