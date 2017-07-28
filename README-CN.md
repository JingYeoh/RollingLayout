# RollingLayout
## Introduction
一个可以让自己子视图滚动起来的控件(仿[淘宝头条])。([English README](README.md))

[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
<img src="https://img.shields.io/badge/license-Apache 2.0-green.svg?style=flat">
[![SDK](https://img.shields.io/badge/API-12%2B-green.svg?style=flat)](https://android-arsenal.com/api?level=11)

## 演示
>提供四种不同的滚动模式.
<img src="/gif/demo.gif" width="280px"/>

## 特性
- [x] **提供四种不同的滚动模式.**
- [x] **可以直接当做FrameLayout来使用.**
- [x] **提供适配器模式支持.**
- [x] **提供滚动变化时候的监听器.**
- [x] **提供子视图的点击监听器.**
- [x] **可配置滚动动画时长.**
- [x] **可配置停留时长.**

## 版本
|名称|RollingLayout|
|---|---|
|最新版|![Download](https://api.bintray.com/packages/jkb/maven/rollinglayout/images/download.svg)|

## 配置
#### Maven
```xml
<dependency>
  <groupId>com.justkiddingbaby</groupId>
  <artifactId>rollinglayout</artifactId>
  <version>最新版</version>
  <type>pom</type>
</dependency>
```
#### JCenter
第一步. 在项目的build.gradle中添加.
```gradle
repositories {
    jcenter()
}
```
然后. 在module的build.gradle中添加.
```gradle
compile 'com.justkiddingbaby:rollinglayout:最新版'
```
## 属性说明
|属性|说明|取值|
|---|---|---|
|[rolling_eachTime](/library/src/main/res/values/attrs.xml)|滚动动画时长|integer|
|[rolling_pause](/library/src/main/res/values/attrs.xml)|停留时长|integer|
|[rolling_orientation](/library/src/main/res/values/attrs.xml)|滚动模式|upDown downUp leftRight rightLeft|

## 方法说明
|返回值|方法名|说明|
|---|---|---|
|void|[setRollingEachTime(int time)](/library/src/main/java/com/jkb/rollinglayout/RollingLayoutAction.java)|设置动画时长|
|void|[setRollingPauseTime(int time)](/library/src/main/java/com/jkb/rollinglayout/RollingLayoutAction.java)|设置停留时长|
|void|[setRollingOrientation(int orientation)](/library/src/main/java/com/jkb/rollinglayout/RollingLayoutAction.java)|设置滚动模式|
|void|[setAdapter(BaseAdapter orientation)](/library/src/main/java/com/jkb/rollinglayout/RollingLayoutAction.java)|设置适配器|
|void|[startRolling()](/library/src/main/java/com/jkb/rollinglayout/RollingLayoutAction.java)|开始滚动|
|void|[stopRolling()](/library/src/main/java/com/jkb/rollinglayout/RollingLayoutAction.java)|停止滚动|
|void|[addOnRollingChangedListener(OnRollingChangedListener listener)](/library/src/main/java/com/jkb/rollinglayout/RollingLayoutAction.java)|添加滚动时候的监听器|
|void|[addOnRollingChangedListener(OnRollingChangedListener listener)](/library/src/main/java/com/jkb/rollinglayout/RollingLayoutAction.java)|添加子视图的点击监听器|

## 使用
#### 在布局中使用
```xml
 <com.jkb.rollinglayout.RollingLayout
            android:id="@+id/rollingleftRight"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_margin="10dp"
            android:layout_weight="1"
            rolling:rolling_eachTime="500"
            rolling:rolling_orientation="leftRight"
            rolling:rolling_pause="1000"/>
```

## 发布历史
#### v1.0.1(2017/7/28)
1、修改父类为ViewFlipper，原本为ViewGroup.
2、添加监听器.
#### v0.1(2017/7/24)
1、发布版本0.1.