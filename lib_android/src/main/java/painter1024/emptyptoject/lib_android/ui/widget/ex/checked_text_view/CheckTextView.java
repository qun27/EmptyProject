package painter1024.emptyptoject.lib_android.ui.widget.ex.checked_text_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SoundEffectConstants;
import android.widget.CheckedTextView;

/**
 * 可切换的TextView，CheckBox的替代品
 */

public class CheckTextView extends CheckedTextView{
    public CheckTextView(Context context) {
        super(context);
    }

    public CheckTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean performClick() {
        toggle();

        final boolean handled = super.performClick();
        if (!handled) {
            // View only makes a sound effect if the onClickListener was
            // called, so we'll need to make one here instead.
            playSoundEffect(SoundEffectConstants.CLICK);
        }

        return handled;
    }
}
