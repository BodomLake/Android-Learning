package com.ttit.jetpack.paging.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.ttit.jetpack.paging.datasource.MovieBoundaryCallback;
import com.ttit.jetpack.paging.datasource.MovieDataSource;
import com.ttit.jetpack.paging.datasource.MovieDataSourceFactory;
import com.ttit.jetpack.paging.db.MovieDao;
import com.ttit.jetpack.paging.db.MyDataBase;

public class MovieViewModel extends ViewModel {

    private Application application;
    private static final int PAGE_SIZE = 8;
    public LiveData<PagedList<Movie>> moviePagedList;

    //LiveData<PagedList<Movie>> moviePagedList
    public MovieViewModel(@Nullable Application application) {
        this.application = application;
        // 从数据源加载数据所需配置
        PagedList.Config config = new PagedList.Config.Builder()
                // 设置控件占位？ 默认为true，快速响应，占据内存
                .setEnablePlaceholders(false)
                // 设置每一页的数据量
                .setPageSize(MovieDataSource.PER_PAGE)
                // 设置当距离底部还有多少条数据的时候开始加载下一页
                .setPrefetchDistance(2)
                // 设置首次加载的数量
                .setInitialLoadSizeHint(2 * MovieDataSource.PER_PAGE)
                .setMaxSize(65535 * MovieDataSource.PER_PAGE)
                .build();

        // 作为工厂生产DataSource资源：MovieDataSourceFactory 继承了 DataSourceFactory.Factory
        // PagedList.Config决定了什么时候触发DataSource资源的供给动作
        this.moviePagedList = new LivePagedListBuilder<>(
                new MovieDataSourceFactory(), config
        ).build();


        /**
         * @setBoundaryCallback
         * If you are paging from a DataSource.Factory backed by local storage,
         * you can set a BoundaryCallback to know when there is no more information to page from local storage.
         * This is useful to page from the network when local storage is a cache of network data.
         */
        // 把网络数据源 换成 本地数据源
        // 没过多少页，就去触发MovieBoundaryCallback回调
        // 可以说Jetpack-Paging组件设计得和Jetpack-Room紧密地联系在一起
/*        MovieDao movieDao = MyDataBase.getInstance(application).getMovieDao();
        this.moviePagedList = new LivePagedListBuilder<>(movieDao.getMovieList(), PAGE_SIZE)
                .setBoundaryCallback(new MovieBoundaryCallback(application))
                .build();*/

    }

    // 清空数据库数据
    public void refresh() {
        AsyncTask.execute(() -> MyDataBase.getInstance(application).getMovieDao().clear());
    }
}
