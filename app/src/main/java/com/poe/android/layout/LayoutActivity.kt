package com.poe.android.layout

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.poe.android.R

/**
 * Android 布局（Layout）系统 - 快速复习版
 *
 * ## 📊 优先级速查表
 *
 * | 技术 | 频率 | 优先级 | 说明 |
 * |------|------|--------|------|
 * | ConstraintLayout | ⭐⭐⭐⭐⭐ | P0 | 90%+ 场景首选 |
 * | LinearLayout | ⭐⭐⭐⭐ | P0 | 简单排列必用 |
 * | ViewBinding | ⭐⭐⭐⭐⭐ | P0 | 替代 findViewById |
 * | FrameLayout | ⭐⭐⭐ | P1 | Fragment 容器 |
 * | include/merge | ⭐⭐⭐⭐ | P1 | 布局复用 |
 * | CoordinatorLayout | ⭐⭐ | P2 | Material Design |
 * | RelativeLayout | ⭐ | P3 | 已淘汰 |
 *
 * ---
 *
 * ## 一、常用布局类型
 *
 * ### 🔥 ConstraintLayout - 【P0】
 * **特点**：通过约束关系定位，扁平化层级，性能最优  
 * **关键属性**：`layout_constraintStart_toStartOf`、`layout_constraintTop_toBottomOf`、链(Chain)、屏障(Barrier)  
 * **场景**：几乎所有 Activity/Fragment 根布局
 *
 * ### 🔥 LinearLayout - 【P0】
 * **特点**：水平/垂直线性排列  
 * **关键属性**：`orientation`(horizontal/vertical)、`gravity`、`layout_weight`  
 * **场景**：按钮组、表单项、Toolbar 内元素排列
 *
 * ### 📌 FrameLayout - 【P1】
 * **特点**：子视图堆叠，后添加的覆盖在先添加的上层  
 * **场景**：Fragment 容器、图片占位符、悬浮层
 *
 * ### ⚠️ RelativeLayout - 【P3 不推荐】
 * **状态**：已被 ConstraintLayout 取代，仅用于维护旧项目
 *
 * ### 🔧 GridLayout / TableLayout - 【P2 低频】
 * **GridLayout**：网格布局，计算器/相册网格（但更多用 RecyclerView）  
 * **TableLayout**：表格布局，极少使用
 *
 * ### 📌 CoordinatorLayout - 【P2】
 * **特点**：支持滚动联动、折叠工具栏  
 * **搭配**：AppBarLayout、CollapsingToolbarLayout、FloatingActionButton  
 * **场景**：电商详情页、新闻文章页
 *
 * ## 二、核心概念
 *
 * ### LayoutParams（布局参数）
 * - **宽高**：`match_parent`、`wrap_content`、具体数值（如 `100dp`）
 * - **Margin**：外边距（视图之间的距离）
 * - **Padding**：内边距（内容与边框的距离）
 * - **规范**：统一使用 dimens.xml 管理，遵循 8dp 网格系统
 *
 * ## 三、最佳实践
 *
 * ### 1. 减少嵌套层级 - 【P0】
 * - 目标：< 10 层，理想 < 5 层
 * - 工具：Layout Inspector 检查层级
 *
 * ### 2. 单位规范 - 【P0】
 * - 尺寸用 `dp`，字体用 `sp`，禁止用 `px`
 *
 * ### 3. 布局复用 - 【P1】
 * - `<include>`：复用公共布局（Toolbar、空状态页等）
 * - `<merge>`：避免额外根节点层级
 *
 * ### 4. 延迟加载 - 【P2】
 * - `ViewStub`：不常用复杂视图按需加载
 *
 * ### 5. 响应式布局 - 【P1】
 * - 目录：`layout/`、`layout-land/`、`layout-sw600dp/`、`layout-night/`
 * - 建议：优先用 ConstraintLayout 弹性特性，而非多套布局
 *
 * ## 四、代码操作
 *
 * ### ViewBinding（必须使用）- 【P0】
 * ```kotlin
 * private val binding get() = _binding!!
 * 
 * override fun onCreate(savedInstanceState: Bundle?) {
 *     super.onCreate(savedInstanceState)
 *     _binding = ActivityLayoutBinding.inflate(layoutInflater)
 *     setContentView(binding.root)
 *     
 *     binding.textView.text = "Hello"  // 直接使用
 * }
 * ```
 * **优势**：类型安全、空安全、性能优于 findViewById
 *
 * ### 动态修改参数 - 【P1】
 * ```kotlin
 * val params = textView.layoutParams as ConstraintLayout.LayoutParams
 * params.width = 200.dpToPx()
 * textView.layoutParams = params
 * ```
 * **建议**：优先在 XML 中控制，少用代码修改
 *
 * ## Summary
 *
 * ### 🎯 学习路径
 * **P0（立即掌握）**：ConstraintLayout、LinearLayout、ViewBinding、dp/sp、Margin/Padding  
 * **P1（1-2月）**：FrameLayout、include/merge、响应式布局、性能优化  
 * **P2（按需）**：CoordinatorLayout、ViewStub、GridLayout  
 * **P3（忽略）**：RelativeLayout、TableLayout
 *
 * ### 💡 决策树
 * ```
 * 复杂布局 → ConstraintLayout ✅
 * 简单排列 → LinearLayout ✅
 * Fragment 容器 → FrameLayout ✅
 * 滚动联动 → CoordinatorLayout ✅
 * 列表数据 → RecyclerView ✅
 * ```
 *
 * ### 📋 团队规范
 * 1. 统一 ViewBinding，禁用 findViewById
 * 2. 尺寸用 dimens.xml，禁硬编码
 * 3. 布局层级 < 10 层
 * 4. 公共布局用 include 复用
 * 5. 遵循 8dp 网格系统   
 */
class LayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_layout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}