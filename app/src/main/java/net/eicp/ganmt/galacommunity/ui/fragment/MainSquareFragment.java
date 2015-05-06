package net.eicp.ganmt.galacommunity.ui.fragment;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;

import net.eicp.ganmt.galacommunity.R;
import net.eicp.ganmt.galacommunity.ui.view.DraggableGridView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ganmt on 2015/5/5.
 */
public class MainSquareFragment extends BaseTabFragment {
    static Random random = new Random();
    static String[] words = "the of and a to in is be that was he for it with as his I on have at by not they this had are but from or she an which you one we all were her would there their will when who him been has more if no out do so can what up said about other into than its time only could new them man some these then two first may any like now my such make over our even most me state after also made many did must before back see through way where get much go well your know should down work year because come people just say each those take day good how long Mr own too little use US very great still men here life both between old under last never place same another think house while high right might came off find states since used give against three himself look few general hand school part small American home during number again Mrs around thought went without however govern don't does got public United point end become head once course fact upon need system set every war put form water took".split(" ");
    DraggableGridView dgv;
    ArrayList<String> poem = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_square,
                container, false);
        dgv = ((DraggableGridView) view.findViewById(R.id.vgv));
        setListeners();
        return view;
    }

    private void setListeners() {
        dgv.setOnRearrangeListener(new DraggableGridView.OnRearrangeListener() {
            public void onRearrange(int oldIndex, int newIndex) {
                String word = poem.remove(oldIndex);
                if (oldIndex < newIndex)
                    poem.add(newIndex, word);
                else
                    poem.add(newIndex, word);
            }
        });
        dgv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //dgv.removeViewAt(arg2);
                // poem.remove(arg2);
            }
        });

        for (int i = 0; i < 6; i++) {
            String word = words[random.nextInt(words.length)];
            ImageView view = new ImageView(getActivity());
            view.setImageBitmap(getThumb(word));
            dgv.addView(view);
            poem.add(word);
        }


    }

    private Bitmap getThumb(String s) {
        Bitmap bmp = Bitmap.createBitmap(150, 150, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();

        paint.setColor(Color.rgb(random.nextInt(128), random.nextInt(128), random.nextInt(128)));
        paint.setTextSize(24);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        canvas.drawRect(new Rect(0, 0, 150, 150), paint);
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(s, 75, 75, paint);

        return bmp;
    }
}
