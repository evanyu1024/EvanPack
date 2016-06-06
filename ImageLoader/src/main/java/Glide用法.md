### Glide使用笔记
### 添加依赖
    compile 'com.github.bumptech.glide:glide:3.7.0'
    compile 'com.android.support:support-v4:19.1.0'
> PS: Glide需要依赖Support Library v4,不过v4支持包在现在的项目中基本已经成为标配

### 基本使用
    Glide.with(context).load(Constant.url).into(mImageView);
> 在这里使用的上下文除了是Context以外,还可以是Activity,Fragment等类型

### 常用设置
1. 设置bitmap格式
    Glide加载的bitmap的格式默认是RGB_565(比ARGB_8888格式的内存开销要小一半)