# Pokemon By Rock

这是一个试图使用 Java 复制 Pokemon For GBA 的尝试.

我本身Java开发工程师,总是想要复刻一些童年中的游戏,但却没有任何开发游戏的相关经验.

所以,这是个练手学习用的工程,我会借此不断熟悉LibGdx的API和游戏开发逻辑.

这个工程将会一直开发下去,直到我放弃使用Java开发游戏或其他原因.

素材基底基于绿宝石.

学习和灵感来自 : [https://www.youtube.com/playlist?list=PLVOwyy-CHLyrFO9A60_z0Q_x8RfpvgrbM]

# 起始时间 

2022 10 12

# 环境及依赖

Java 11


Gradle 6.9.2


LibGdx for PC 1.11.0


尽量不用入侵性比较强的东西,比如Lombok之类东西.

# 启动方式

use IDE import build.gradle

run class DesktopLauncher


# 目前拥有的东西

一个人物的行走:上下左右移动判定、撞墙原地踏步判定+音效+慢动画

简陋的未白镇(只有草地)

简单的相机功能,让世界始终以主角为中心

世界的逻辑,里面包括 人物、事物、地图网格等等