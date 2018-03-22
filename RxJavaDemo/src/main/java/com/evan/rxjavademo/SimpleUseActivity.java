package com.evan.rxjavademo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by evanyu on 16/8/2.
 */
public class SimpleUseActivity extends AppCompatActivity {

    @BindView(R.id.iv)
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_use);
        ButterKnife.bind(this);
    }

    public void test1(View view) {
//        Observable.create((Subscriber<? super String> subscriber) -> {
//
//        });


//        final String[] arr = {"111", "222", "333"};
//        Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext(arr[0]);
//                subscriber.onNext(arr[1]);
//                subscriber.onNext(arr[2]);
//                subscriber.onCompleted();
//            }
//        }).subscribe(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                Logger.d(s);
//            }
//        });
    }

    public void test2(View view) {
        Observable.just("aaa", "bbb", "ccc")
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d("error!!!");
                    }

                    @Override
                    public void onNext(String s) {
                        int i = 1 / 0;
                        Logger.d(s);
                    }
                });
    }

    public void test3(View view) {
        List<Student> stus = new ArrayList<>();
        stus.add(new Student("aaa", 111));
        stus.add(new Student("bbb", 222));
        stus.add(new Student("ccc", 333));
        Observable.from(stus)
                .map(new Func1<Student, String>() {
                    @Override
                    public String call(Student student) {
                        return student.introduce();
                    }
                }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String stuInfo) {
                Logger.d(stuInfo);
            }
        });
    }

    public void test4(View view) {
        List<Student> stus = new ArrayList<>();
        stus.add(new Student("aaa", 111, "str11", "str12", "str13"));
        stus.add(new Student("bbb", 222, "str21", "str22", "str23"));
        stus.add(new Student("ccc", 333, "str31", "str32", "str33"));

        Observable.from(stus)
                .flatMap(new Func1<Student, Observable<String>>() {
                    @Override
                    public Observable<String> call(Student stu) {
                        return Observable.just(stu.str1, stu.str2, stu.str3);
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Logger.d(s);
                    }
                });
    }

    public void test5(View view) {
        Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                Logger.d("call()..." + Thread.currentThread());
                // SystemClock.sleep(2000);
                Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                subscriber.onNext(bm);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Bitmap>() {
                               @Override
                               public void onStart() {
                                   Logger.d("onStart()..." + Thread.currentThread());
                                   super.onStart();
                               }

                               @Override
                               public void onCompleted() {
                                   Logger.d("onCompleted()..." + Thread.currentThread());
                                   Toast.makeText(SimpleUseActivity.this, "加载完成", Toast.LENGTH_SHORT);
                               }

                               @Override
                               public void onError(Throwable e) {
                                   Logger.d("onError()..." + Thread.currentThread());
                               }

                               @Override
                               public void onNext(Bitmap bitmap) {
                                   Logger.d("onNext()..." + Thread.currentThread());
                                   mImageView.setImageBitmap(bitmap);
                               }
                           }
                );
    }

    class Student {

        String name;
        int age;

        String str1, str2, str3;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Student(String name, int age, String str1, String str2, String str3) {
            this.name = name;
            this.age = age;
            this.str1 = str1;
            this.str2 = str2;
            this.str3 = str3;
        }

        public String introduce() {
            // Logger.d(name + "..." + age);
            return name + "..." + age;
        }
    }

}
