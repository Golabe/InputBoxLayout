# InputBoxLayout(验证码密码输入框)
##### //todo 输入类型未实现
## 使用

<img src="https://github.com/Golabe/InputBoxLayout/blob/master/images/a1.png?raw=true" width=220 height=400 /><img src="https://github.com/Golabe/InputBoxLayout/blob/master/images/a2.png?raw=true" width=220 height=400 />
<img src="https://github.com/Golabe/InputBoxLayout/blob/master/images/a3.png?raw=true" width=220 height=400 /><img src="https://github.com/Golabe/InputBoxLayout/blob/master/images/a4.png?raw=true" width=220 height=400 />

### GRADLE 

```xml
 implementation 'top.golabe.InputBoxLayout:library:1.1.0'
```

### XML 
```xml
  <top.golabe.kotlin.library.InputBoxLayout
       app:box_bg_focus="@drawable/bg_box_bg_fcous"
       android:id="@+id/ibl_input"
       app:box_size="20dp"
       app:box_number="4"
       app:box_bg_normal="@drawable/bg_box_bg_fcous"
       app:h_padding="2dp"
       app:text_color="@color/colorPrimary"
       app:v_padding="2dp"
       app:text_size="12sp"
       app:input_type="text_password"
       android:layout_width="match_parent"
       android:layout_height="wrap_content"/>
```


### 默认输入框背景
####  app:box_bg_focus
```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android">

    <solid android:color="@android:color/white"/>
    <corners android:radius="8dp"/>
    <stroke
        android:width="2dp"
        android:color="@android:color/holo_orange_dark"/>

</shape>
```

####  app:box_bg_normal
```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android">

    <solid android:color="@android:color/white"/>
    <corners android:radius="8dp"/>
    <stroke
        android:width="1dp"
        android:color="@android:color/darker_gray"/>

</shape>
```
### JAVA

```java
   inputBoxLayout=findViewById(R.id.ibl_input);
        inputBoxLayout.setBoxBgFocus();
        inputBoxLayout.setBoxBgNormal();
        inputBoxLayout.setBoxNumber();
        inputBoxLayout.setBoxSize();
        inputBoxLayout.sethPadding();
        inputBoxLayout.setInputType();
        inputBoxLayout.setTextColor();
        inputBoxLayout.setvPadding();
        inputBoxLayout.setOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(String content) {
                Toast.makeText(MainActivity.this,content,Toast.LENGTH_LONG).show();
            }
        });

//重置
inputBoxLayout.reset()
```
### ATTRS
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <declare-styleable name="InputBoxLayout">

        <!--输入框数量-->
        <attr name="box_number" format="integer" />
        <!--输入文字大小-->
        <attr name="text_size" format="dimension" />
        <!--输入文字颜色-->
        <attr name="text_color" format="color" />
        <!--输入框默认背景-->
        <attr name="box_bg_normal" format="reference" />
        <!--输入框激活时背景-->
        <attr name="box_bg_focus" format="reference" />
        <!--<attr name="padding" format="dimension" />-->
        <!--输入框大小-->
        <attr name="box_size" format="dimension" />
        <!--每个输入框垂直边距-->
        <attr name="v_padding" format="dimension" />
        <!--每个输入框水平边距-->
        <attr name="h_padding" format="dimension" />

        <!--输入类型-->
        <attr name="input_type" format="enum">
            <enum name="number" value="0x0001" />
            <enum name="text" value="0x0002" />
            <enum name="number_password" value="0x0003" />
            <enum name="text_password" value="0x0004" />
        </attr>
    </declare-styleable>
</resources>

```
