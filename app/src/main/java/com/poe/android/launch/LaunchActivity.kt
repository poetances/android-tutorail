package com.poe.android.launch

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.poe.android.R

/**
 * Android 中配置第一个启动 Activity 的方式及 Manifest 常用配置详解
 *
 * ## 一、配置第一个启动 Activity 的方式
 *
 * ### 1. Intent Filter 方式（最常用）
 * 在 AndroidManifest.xml 中为 Activity 添加 intent-filter，指定其为主入口：
 * ```xml
 * <activity android:name=".LaunchActivity">
 *     <intent-filter>
 *         <action android:name="android.intent.action.MAIN" />
 *         <category android:name="android.intent.category.LAUNCHER" />
 *     </intent-filter>
 * </activity>
 * ```
 * - `action.MAIN`: 表示这是应用的主入口点
 * - `category.LAUNCHER`: 表示该 Activity 会显示在系统应用启动器中
 *
 * ### 2. 在Configurations中配置。
 *
 * 注意：不管哪种配置，都需要讲 activity的exported 属性设置为 true
 * ```
 *
 * ## 二、Activity 在 Manifest 中的常用配置属性
 *
 * ### 1. 基础属性
 * - `android:name`: Activity 的类名（必需）
 * - `android:label`: Activity 显示的标题名称（可选，若不配置则继承 Application 的 label）
 * - `android:icon`: Activity 的图标（可选，若不配置则继承 Application 的 icon）
 * - `android:theme`: Activity 的主题样式（可选，若不配置则继承 Application 的 theme）
 *
 * **注意**：`label`、`icon`、`theme` 这些属性通常在 `<application>` 标签中统一配置，
 * 所有 Activity 会默认继承。只有在某个 Activity 需要特殊显示时，才在 Activity 级别单独配置。
 *
 * ### 2. 屏幕方向配置
 * - `android:screenOrientation`: 控制屏幕方向
 *   - `portrait`: 竖屏
 *   - `landscape`: 横屏
 *   - `sensor`: 根据传感器自动旋转
 *   - `unspecified`: 默认值，由系统决定
 *
 * ### 3. 启动模式（Launch Mode）
 * - `android:launchMode`: 定义 Activity 的启动行为
 *   - `standard`: 标准模式，每次启动都创建新实例（默认）
 *   - `singleTop`: 如果栈顶已是该 Activity，则复用，否则创建新实例
 *   - `singleTask`: 在任务栈中只存在一个实例，若已存在则将其上方的 Activity 全部弹出
 *   - `singleInstance`: 单独的任务栈，且栈中只有这一个 Activity
 *
 * ### 4. 窗口相关配置
 * - `android:windowSoftInputMode`: 软键盘弹出时的行为
 *   - `stateHidden`: 默认隐藏软键盘
 *   - `adjustResize`: 调整窗口大小以适应软键盘
 *   - `adjustPan`: 平移窗口内容以避免被软键盘遮挡
 *
 * ### 5. 权限和安全配置
 * - `android:permission`: 启动该 Activity 所需的权限
 * - `android:exported`: 是否可被其他应用启动（API 31+ 必须显式声明）
 *   - `true`: 可被外部应用启动
 *   - `false`: 只能被同一应用或具有相同 UID 的应用启动
 *
 * ### 6. 其他常用配置
 * - `android:configChanges`: 声明自行处理的配置变化，避免 Activity 重建
 *   - 常见值: `orientation|screenSize|keyboardHidden`
 * - `android:noHistory`: Activity 离开后是否从任务栈中移除（`true`/`false`）
 * - `android:parentActivityName`: 指定父 Activity，用于向上导航（Up Navigation）
 * - `android:taskAffinity`: 定义 Activity 所属的任务栈名称
 *
 * ### 7. 完整示例
 * ```xml
 * <activity
 *     android:name=".LaunchActivity"
 *     android:label="@string/app_name"
 *     android:theme="@style/AppTheme"
 *     android:screenOrientation="portrait"
 *     android:launchMode="singleTop"
 *     android:windowSoftInputMode="stateHidden|adjustResize"
 *     android:exported="true"
 *     android:configChanges="orientation|screenSize">
 *     <intent-filter>
 *         <action android:name="android.intent.action.MAIN" />
 *         <category android:name="android.intent.category.LAUNCHER" />
 *     </intent-filter>
 * </activity>
 * ```
 *
 * ## Summary
 *
 * **配置启动 Activity 的核心要点：**
 * 1. **Intent Filter 是标准方式**: 通过 `action.MAIN` + `category.LAUNCHER` 组合声明主入口
 * 2. **支持多入口**: 可为多个 Activity 配置 LAUNCHER，实现多个桌面图标
 * 3. **Activity Alias 提供灵活性**: 适合动态更换图标、名称等场景
 * 4. **exported 属性至关重要**: API 31+ 必须显式声明，涉及应用安全性
 *
 * **Manifest 配置的关键分类：**
 * - **基础标识**: name、label、icon、theme
 * - **屏幕控制**: screenOrientation、configChanges
 * - **任务栈管理**: launchMode、taskAffinity、noHistory
 * - **交互体验**: windowSoftInputMode
 * - **安全权限**: permission、exported
 * - **导航关系**: parentActivityName
 */
class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_launch)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}