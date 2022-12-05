package com.ttit.jetpack.recyclerview;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class IdolUtil {
    @NonNull
    public static List<Idol> getIdols() {
        List<Idol> idolList = new ArrayList<>();
        Idol i0 = new Idol("https://img2.baidu.com/it/u=3577641584,1029641277&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1663434000&t=e4bd1dcfc15efd8a688daf7a7bc9a8c6","斯嘉丽·约翰逊", "Scarlett Johansson");
        Idol i1 = new Idol("https://img0.baidu.com/it/u=3855945177,3646147650&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1663434000&t=9c8d43125baa8e764ef1f312a8a64ae1","安吉丽娜·朱莉", "Angelina Jolie");
        Idol i2 = new Idol("https://img1.baidu.com/it/u=1215922565,828699866&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1663434000&t=d69e2d505168033b4b0c09fa67fde2c3","杰西卡·辛普森", "Jessica Simpson");
        idolList.add(i0);
        idolList.add(i1);
        idolList.add(i2);
        return idolList;
    }
}
