# Android 项目结构与命名规范

## 1. 整体项目结构

```
android-tutorial/                    # 项目根目录
├── app/                            # 应用模块目录
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/poe/android/    # Java/Kotlin源代码
│   │   │   ├── res/                     # 资源文件
│   │   │   └── AndroidManifest.xml      # 应用清单文件
│   │   ├── androidTest/                 # 仪器化测试
│   │   └── test/                        # 单元测试
│   ├── build.gradle.kts               # 模块级构建配置
│   └── proguard-rules.pro             # 代码混淆规则
├── gradle/                           # Gradle包装器和版本目录
├── build.gradle.kts                  # 项目级构建配置
├── settings.gradle.kts               # 项目设置
└── local.properties                  # 本地属性配置
```

---

## 2. 核心目录说明

### 📁 项目文件夹命名规范

| 目录 | 命名规范 | 示例 |
|------|----------|------|
| **项目根目录** | 小写字母+连字符 | `android-tutorial` |
| **模块目录** | 小写字母 | `app`, `library` |
| **源代码目录** | 固定名称 | `src/main/java` |
| **资源目录** | 固定名称 | `src/main/res` |

### 📦 包名（Package Name）命名规范

在你的项目中,包名为:`com.poe.android`

**包名格式:** `顶级域名.组织/公司名.项目名.模块名`
**常见示例:**
```
com.google.android.maps          # Google的地图应用
com.tencent.mm                   # 腾讯微信
com.taobao.taobao                # 淘宝应用
com.poe.android                  # 你的项目
```

---

## 3. 关键配置文件中的命名

### 📄 settings.gradle.kts
```kotlin
rootProject.name = "AndroidTutorial"  // 项目名称:大驼峰命名
include(":app")                       // 模块名称:小写
```

### 📄 build.gradle.kts (app模块)
```kotlin
android {
    namespace = "com.poe.android"     // 命名空间:与包名一致
    applicationId = "com.poe.android" // 应用ID:唯一标识
    
    defaultConfig {
        versionCode = 1               // 版本号:整数
        versionName = "1.0"           // 版本名:字符串
    }
}
```

---

## 4. 资源文件命名规范

### 🎨 drawable/ 目录
```
ic_baseline_download.xml    # 图标:ic_前缀 + 描述
ic_launcher_background.xml  # 启动器背景
ic_launcher_foreground.xml  # 启动器前景
```

**命名规则:**
- 小写字母 + 下划线
- 使用有意义的描述性名称
- 按类型添加前缀:
  - `ic_` - 图标 (icon)
  - `bg_` - 背景 (background)
  - `btn_` - 按钮 (button)

### 📐 layout/ 目录
```
activity_main.xml           # Activity布局:activity_前缀
activity_text.xml           # Activity布局
fragment_home.xml           # Fragment布局:fragment_前缀
item_user.xml               # RecyclerView项:item_前缀
dialog_confirm.xml          # 对话框:dialog_前缀
```

### 📝 values/ 目录
```
strings.xml                 # 字符串资源
colors.xml                  # 颜色资源
themes.xml                  # 主题资源
```

---

## 5. 类文件命名规范

### 🏷️ Activity/Fragment 命名
```kotlin
MainActivity.kt             # 主Activity:功能名 + Activity后缀
TextActivity.kt             # 文本Activity
HomeFragment.kt             # 主页Fragment
UserAdapter.kt              # 用户适配器
```

**命名原则:**
- ✅ 大驼峰命名法(PascalCase)
- ✅ 清晰表达功能和用途
- ✅ 使用相应的组件后缀

---

## 6. Summary

### 📋 Android项目命名规范要点

**🗂️ 文件夹命名:**
- 项目名:小写字母+连字符(如 `android-tutorial`)
- 模块名:全小写(如 `app`)
- 系统目录:使用标准名称(`java`, `res`, `drawable`等)

**📦 包名规范:**
- 格式:`域名.公司名.项目名`
- 全部小写,用点号分隔
- 具有唯一性,避免冲突

**🏷️ 类文件命名:**
- 大驼峰命名法
- 添加组件后缀(Activity, Fragment, Adapter等)
- 清晰描述功能

**🎨 资源文件命名:**
- 小写字母+下划线
- 添加类型前缀(ic_, bg_, activity_等)
- 语义化命名,便于理解

这些规范有助于保持项目的可维护性和团队协作效率。


