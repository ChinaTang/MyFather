package tang.di.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by tangdi on 2015/11/13.
 */
public class MyFragmentDialog extends DialogFragment {

    public static MyFragmentDialog newInstance(Bundle bundle){
        MyFragmentDialog dialog = new MyFragmentDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

}
