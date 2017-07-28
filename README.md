# RollingLayout
## Introduction
>A view that can rolling automatic within child views list.
一个可以让自己子视图滚动起来的控件(仿[淘宝头条])。([中文版入口](README-CN.md))  
[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
<img src="https://img.shields.io/badge/license-Apache 2.0-green.svg?style=flat">
[![SDK](https://img.shields.io/badge/API-12%2B-green.svg?style=flat)](https://android-arsenal.com/api?level=11)

## Demo
>Provide four direction rolling mode.
<img src="/gif/demo.gif" width="280px"/>

## Features
- [x] **Provide four direction for rolling mode.**
- [x] **It can be used as FrameLayout.**
- [x] **Provide adapter pattern.**
- [x] **Provide listener to listen rolling changing action.**
- [x] **Provide listener to listen rolling item clicking action.**
- [x] **Configure duration of stay for rolling item view.**
- [x] **Configure duration of rolling time for item view.**

## Version
|name|RollingLayout|
|---|---|
|latest|![Download](https://api.bintray.com/packages/jkb/maven/rollinglayout/images/download.svg)|

## Configure
#### Maven
```xml
<dependency>
  <groupId>com.justkiddingbaby</groupId>
  <artifactId>rollinglayout</artifactId>
  <version>the latest version</version>
  <type>pom</type>
</dependency>
```
#### JCenter
First. add to project build.gradle
```gradle
repositories {
    jcenter()
}
```
Second. add to module build.gradle
```gradle
compile 'com.justkiddingbaby:rollinglayout:the latest version'
```
## Attributes instruction
|attribute|instruction|value|
|---|---|---|
|[rolling_eachTime](/library/src/main/res/values/attrs.xml)|duration for rolling animator|integer|
|[rolling_pause](/library/src/main/res/values/attrs.xml)|duration of stay for rolling animator|integer|
|[rolling_orientation](/library/src/main/res/values/attrs.xml)|rolling mode|upDown downUp leftRight rightLeft|

## Function instruction
|return|function name|instruction|
|---|---|---|
|void|[setRollingEachTime(int time)](/library/src/main/java/com/jkb/rollinglayout/RollingLayoutAction.java)|set duration of rolling|
|void|[setRollingPauseTime(int time)](/library/src/main/java/com/jkb/rollinglayout/RollingLayoutAction.java)|set duration of stay|
|void|[setRollingOrientation(int orientation)](/library/src/main/java/com/jkb/rollinglayout/RollingLayoutAction.java)|set the rolling mode|
|void|[setAdapter(BaseAdapter orientation)](/library/src/main/java/com/jkb/rollinglayout/RollingLayoutAction.java)|set the data behind this RollingLayout|
|void|[startRolling()](/library/src/main/java/com/jkb/rollinglayout/RollingLayoutAction.java)|start rolling|
|void|[stopRolling()](/library/src/main/java/com/jkb/rollinglayout/RollingLayoutAction.java)|stop rolling|
|void|[addOnRollingChangedListener(OnRollingChangedListener listener)](/library/src/main/java/com/jkb/rollinglayout/RollingLayoutAction.java)|add a listener to listen RollingLayout rolling changed action.|
|void|[addOnRollingChangedListener(OnRollingChangedListener listener)](/library/src/main/java/com/jkb/rollinglayout/RollingLayoutAction.java)|set a listener to listen RollingLayout rolling item click action.|

## Usage
#### use in the layout
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

## Release history
#### v1.0.1(2017/7/28)
1、alter parent class to ViewFlipper from ViewGroup.
2、add listener for RollingLayout.
#### v0.1(2017/7/24)
1、release version 0.1.