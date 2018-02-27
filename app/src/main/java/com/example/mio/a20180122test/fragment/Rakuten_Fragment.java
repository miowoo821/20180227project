package com.example.mio.a20180122test.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.mio.a20180122test.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Rakuten_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Rakuten_Fragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    WebView wv;
    View view;
    Button btn;
    public Rakuten_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Rakuten_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Rakuten_Fragment newInstance(String param1, String param2) {
        Rakuten_Fragment fragment = new Rakuten_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rakuten_, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        view=getView();
        wv=view.findViewById(R.id.webview);
        wv.setWebChromeClient(new WebChromeClient());
        wv.setWebViewClient(new WebViewClient());
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl("https://www.rakuten.com.tw/event/");

        btn=(Button)view.findViewById(R.id.pre_btn);
        btn.setOnClickListener(this);
        btn=(Button)view.findViewById(R.id.next_btn);
        btn.setOnClickListener(this);
        btn=(Button)view.findViewById(R.id.index_btn);
        btn.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onClick(View view) {
        Log.d("GGGGGGG","GGGGGGGGGGG");
        switch (view.getId()){
            case R.id.pre_btn:

                if(wv.canGoBack()){
                    wv.goBack();
                }
                break;
            case R.id.next_btn:
                if(wv.canGoForward()){
                    wv.goForward();
                }
                break;
            case R.id.index_btn:
                wv.loadUrl("https://www.rakuten.com.tw/event/");
                break;
        }


    }
}
