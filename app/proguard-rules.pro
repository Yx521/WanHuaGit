# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#指定压缩级别
-optimizationpasses 5

#不跳过非公共的库的类成员
-dontskipnonpubliclibraryclassmembers

#混淆时采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

#把混淆类中的方法名也混淆了
-useuniqueclassmembernames

#优化时允许访问并修改有修饰符的类和类的成员
-allowaccessmodification

#将文件来源重命名为“SourceFile”字符串
-renamesourcefileattribute SourceFile
#保留行号
-keepattributes SourceFile,LineNumberTable
#保持泛型
-keepattributes Signature

#保持所有实现 Serializable 接口的类成员
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#Fragment不需要在AndroidManifest.xml中注册，需要额外保护下
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Fragment

# 保持测试相关的代码
-dontnote junit.framework.**
-dontnote junit.runner.**
-dontwarn android.test.**
-dontwarn android.support.test.**
-dontwarn org.junit.**

# Retrofit
-dontnote retrofit2.Platform
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
-dontwarn retrofit2.Platform$Java8
-keepattributes Signature
-keepattributes Exceptions
-dontwarn javax.annotation.**
-dontwarn sun.misc.**
-dontwarn sorg.codehaus.mojo.animal_sniffer.**
-dontwarn org.codehaus.**
-dontwarn java.nio.**
-dontwarn java.lang.invoke.**

# okhttp
-dontwarn okio.**
-dontwarn okhttp3.**
-dontwarn javax.annotation.**

    ## Square Picasso specific rules ##
    ## https://square.github.io/picasso/ ##
-dontwarn com.squareup.okhttp.**

-keep class org.greenrobot.greendao.**{*;}
 -keep public interface org.greenrobot.greendao.** -keepclassmembers class * extends org.greenrobot.greendao.AbstractDao { public static java.lang.String TABLENAME;
}
-keep class **$Properties -keep class net.sqlcipher.database.**{*;}
 -keep public interface net.sqlcipher.database.** -dontwarn net.sqlcipher.database.** -dontwarn org.greenrobot.greendao.**

# ButterKnife 7

-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#忽略警告
-ignorewarnings
-keep class javax.ws.rs.** { *; }
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.** { *; }

-dontwarn com.baidu.**
-keep class com.baidu.** { *;}

-dontwarn com.lakala.**
-keep class com.lakala.** { *;}

-dontwarn com.lingtu.**
-keep class com.lingtu.** { *;}

-dontwarn com.autonavi.**
-keep class com.autonavi.** { *;}

#EventBus报错解决
#因为打包混淆找不到eventbus类
-keepattributes *Annotation*
-keepclassmembers class ** {
@org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
<init>(java.lang.Throwable);
}

-keep class com.example.lenovo.playandroid.utils.* {*;}

-keepattributes *Annotation*,EnclosingMethod,Signature -keepnames class com.fasterxml.jackson.** { *; }
-dontwarn com.fasterxml.jackson.databind.** -keep class org.codehaus.** { *; }
-keepclassmembers public final enum org.codehaus.jackson.annotate.JsonAutoDetect$Visibility { public static final org.codehaus.jackson.annotate.JsonAutoDetect$Visibility *; }
-keep public class your.class.** { public void set*(***); public *** get*(); }

-keep public class com.example.lenovo.playandroid.beans.**{*;}