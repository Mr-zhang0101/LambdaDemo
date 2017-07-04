package com.zhang.lambdademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static com.zhang.lambdademo.User.sMethod;
import static java.util.Comparator.comparing;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MAIN";
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = (TextView) findViewById(R.id.tv);
        _Lambda1();
        _Lambda2();
        _Lambda3();
        _Lambda4();

    }


    private void _Lambda1() {
        new Thread(()-> Log.d(TAG, "run:_Lambda ")).start();
        mTv.setOnClickListener((View v)-> finish());
    }

    private void _Lambda2() {

        User user = new User();

        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.mMethod(v);
            }
        });

        //Lambda ::的使用1：调用类的成员方法，规则：省略的匿名内部类的参数与调用User的参数一样
        mTv.setOnClickListener(user::mMethod);

        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sMethod(v);
            }
        });

        mTv.setOnClickListener(User::sMethod);

        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new User(v);
            }
        });

        //Lambda ::的使用2：调用类的构造方法，规则：省略的匿名内部类的参数与调用User的参数一样
        mTv.setOnClickListener(User::new);

        Function<User, String> getUserName = User::getUserName;
       // Comparator comparator = comparing(User::getUserName);
        Comparator comparator = Comparator.comparing(new Function<User, String>() {
            @Override
            public String apply(User user) {
                return user.getUserName();
            }
        });
    }


    private void _Lambda3() {
        List<User> users = Arrays.asList(new User[]{new User("Mr.zhang_1"),new User("Mr.zhang_2")});

        users.forEach(user ->Log.d(TAG, "_Lambda3: user"+user.getUserName()));


        for (User user : users){
            Log.d(TAG, "_Lambda3: user"+user.getUserName());
        }
    }

    private void _Lambda4() {
        List<User> users = Arrays.asList(new User[]{new User("Mr.zhang_1"),new User("Mr.zhang_2")});
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return u1.getUserName().compareTo(u2.getUserName());
            }
        });
        users.sort(comparing(User::getUserName));
        //去掉匿名内部类
        Collections.sort(users,(User u1, User u2) ->u1.getUserName().compareTo(u2.getUserName()));
        //使用Comparator.comparing()进行比较
        Collections.sort(users,Comparator.comparing((User u)->u.getUserName()));
        //静态方法可以直接引入
        Collections.sort(users,comparing((User u)->u.getUserName()));
        //Lambda类型推断
        Collections.sort(users,comparing(u->u.getUserName()));
        // :: 双冒号方法引用
        Collections.sort(users,comparing(User::getUserName));
        //使用List的sort方法进一步简化
        users.sort(comparing(User::getUserName));
    }



    public void toast(String msg){
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
