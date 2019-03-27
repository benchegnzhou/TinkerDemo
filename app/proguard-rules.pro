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



-keep public class * implements com.tencent.tinker.loader.app.ApplicationLifeCycle {
    *;
}

-keep public class * extends com.tencent.tinker.loader.TinkerLoader {
    *;
}

-keep public class * extends android.app.Application {
    *;
}

#your dex.loader pattern here
-keep class com.tencent.tinker.loader.** {
    *;
 }

-keep class tinker.sample.android.app.SampleApplication {
    *;
}


#Gson
-keepattributes Signature
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.google.gson.* { *;}
-dontwarn com.google.gson.**

#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }




# 记录生成的日志数据,gradle build时在本项根目录输出
# apk 包内所有 class 的内部结构
-dump proguard/class_files.txt
# 未混淆的类和成员
-printseeds proguard/seeds.txt
# 列出从 apk 中删除的代码
-printusage proguard/unused.txt
# 混淆前后的映射
-printmapping proguard/mapping.txt


# 避免混淆泛型
-keepattributes Signature
# 抛出异常时保留代码行号,保持源文件以及行号
-keepattributes SourceFile,LineNumberTable

#-----------------------------6.默认保留区-----------------------
# 保持 native 方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclassmembers public class * extends android.view.View {
 public <init>(android.content.Context);
 public <init>(android.content.Context, android.util.AttributeSet);
 public <init>(android.content.Context, android.util.AttributeSet, int);
 public void set*(***);
}

#保持 Serializable 不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# 保持自定义控件类不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context,android.util.AttributeSet);
}
# 保持自定义控件类不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context,android.util.AttributeSet,int);
}
# 保持自定义控件类不被混淆
-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
}

# 保持枚举 enum 类不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 保持 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

# 不混淆R文件中的所有静态字段，我们都知道R文件是通过字段来记录每个资源的id的，字段名要是被混淆了，id也就找不着了。
-keepclassmembers class **.R$* {
    public static <fields>;
}

#如果引用了v4或者v7包
-dontwarn android.support.**

# 保持哪些类不被混淆
-keep public class * extends android.app.Appliction
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.preference.Preference

-keep class com.zhy.http.okhttp.**{*;}
-keep class com.wiwide.util.** {*;}


#尝试  参考了网上的解决方案

-dontwarn  sun.security.x509.**
-dontwarn  sun.security.util.**
-keep class sun.security.x509.** {*;}
-keep class sun.security.util.** {*;}

-dontwarn  javax.servlet.http.**
-keep class javax.servlet.http.** {*;}
-dontwarn  javax.servlet.**
-keep class javax.servlet.** {*;}

-dontwarn javax.persistence.**
-keep class javax.persistence.** {*;}

-dontwarn sun.security.rsa.**
-keep class sun.security.rsa.** {*;}

-dontwarn sun.security.jca.**
-keep class sun.security.jca.** {*;}

-dontwarn javax.lang.model.element.**
-keep class javax.lang.model.element.** {*;}

-dontwarn org.joda.time.**
-keep class org.joda.time.** {*;}

-dontwarn org.joda.time.format.**
-keep class org.joda.time.format.** {*;}

-dontwarn org.slf4j.**
-keep class org.slf4j.** {*;}

-dontwarn javax.lang.model.**
-keep class javax.lang.model.** {*;}

-dontwarn javax.annotation.processing.**
-keep class javax.annotation.processing.** {*;}

-dontwarn javax.tools.**
-keep class javax.tools.** {*;}


-dontwarn sun.security.internal.spec.**
-keep class sun.security.internal.spec.** {*;}

-dontwarn sun.security.internal.interfaces.**
-keep class sun.security.internal.interfaces.** {*;}

-dontwarn sun.misc.**
-keep class sun.misc.** {*;}

-dontwarn sun.security.provider.**
-keep class sun.security.provider.** {*;}

-dontwarn org.w3c.dom.bootstra.**
-keep class org.w3c.dom.bootstra.** {*;}

-dontwarn org.w3c.dom.bootstrap.**
-keep class org.w3c.dom.bootstrap.** {*;}

-dontwarn jcifs.**

-dontwarn com.zhy.**


