# Pokemon By Rock

这是一个试图使用 Java 复制 Pokemon For GBA 的尝试.

我本身Java开发工程师,总是想要复刻一些童年中的游戏,但却没有任何开发游戏的相关经验.

所以,这是个练手学习用的工程,我会借此不断熟悉LibGdx的API和游戏开发逻辑.

这个工程将会一直开发下去,直到我放弃使用Java开发游戏或其他原因.

暂时应该只支持Mac环境下开发吧(因为盘符),以后再适配吧.

素材基底基于绿宝石.

学习和灵感来自 : [https://www.youtube.com/playlist?list=PLVOwyy-CHLyrFO9A60_z0Q_x8RfpvgrbM]

![image of the program](https://raw.githubusercontent.com/Rock-Ayl/Rock-Ayl-File.github.io/main/theme.png)

# 起始时间 

2022 10 12

# 环境及依赖

Java 11


Gradle 6.9.2


LibGdx for PC 1.11.0


Apache工具包


阿里FastJson(怎么快怎么来,一个小小demo)


尽量不用入侵性比较强的东西,比如Lombok之类东西.

# 启动方式

use IDE import build.gradle

run class DesktopLauncher

# 操控方式

方向键控制方向

z:确认/交谈/调查

x:取消/跑步

回车:菜单

空格:加速

# 更新日志

<table>
    <thead>
        <tr>
            <th>版本号</th>
            <th>更新时间</th>
            <th>更新内容</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>0.0.3</td>
            <td>-</td>
            <td>基础实体的架设等等...</td>
        </tr>
        <tr>
            <td>0.0.4</td>
            <td>-</td>
            <td>人物基本移动</td>
        </tr>
        <tr>
            <td>0.0.5</td>
            <td>-</td>
            <td>世界、地图的大纲完善.</td>
        </tr>
        <tr>
            <td>0.0.6</td>
            <td>-</td>
            <td>相机、视角、支持屏幕拉伸.</td>
        </tr>
        <tr>
            <td>0.0.7</td>
            <td>-</td>
            <td>增加对话框UI</td>
        </tr>
        <tr>
            <td>0.0.8</td>
            <td>2023-9-9</td>
            <td>增加可选项框UI</td>
        </tr>
        <tr>
            <td>0.0.9</td>
            <td>2023-9-15</td>
            <td>Json配置地图、事物,对大型事物增加多网格体积碰撞.</td>
        </tr>
        <tr>
            <td>0.0.10</td>
            <td>2023-9-18</td>
            <td>事物的移动,完善部分未白镇地图,按住空格统一加速(未实现音乐、音效)</td>
        </tr>
        <tr>
            <td>0.0.11</td>
            <td>2023-9-19</td>
            <td>人物与地图块的关联,修正了一部分UI样式,解决一些bug</td>
        </tr>
        <tr>
            <td>0.0.12</td>
            <td>2023-10-24</td>
            <td>简单的跑,有点动画瑕疵</td>
        </tr>
        <tr>
            <td>0.0.13</td>
            <td>2023-11-12</td>
            <td>跑步动作优化</td>
        </tr>
        <tr>
            <td>0.0.14</td>
            <td>2023-11-13</td>
            <td>菜单音效</td>
        </tr>
        <tr>
            <td>0.0.15</td>
            <td>2023-11-20</td>
            <td>整理了代码,增加了NPC配置类,优化了载入资源的方式</td>
        </tr>
        <tr>
            <td>0.0.16</td>
            <td>2023-11-25</td>
            <td>整合了对话框、可选项,并可以配置</td>
        </tr>
    </tbody>
</table>