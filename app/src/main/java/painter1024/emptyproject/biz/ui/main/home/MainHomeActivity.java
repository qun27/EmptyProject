package painter1024.emptyproject.biz.ui.main.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;

import painter1024.emptyproject.R;
import painter1024.emptyproject.core.ui.base.component_ex.recycler.paging.PagingLoadActivity;
import painter1024.emptyproject.core.ui.ex.controller.navigation.NavigationController;

public class MainHomeActivity extends PagingLoadActivity {

    int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        new NavigationController(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                finishPageLoad(page, true, true);
            }
        });

        setAdapter(new MainHomeAdapter());
    }

    public static Intent prepareIntent(Context context) {
        return new Intent(context, MainHomeActivity.class);
    }

    @Override
    public void onPageLoad(int page) {
        this.page = page;
    }
}
