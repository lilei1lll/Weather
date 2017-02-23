
# Weather

### 基本简介：

> [**风奚天气**](https://github.com/PengHesheng/Weather)是一款简洁风格的天气APP，实现了其他天气APP所具有的基本功能，具有优秀的用户体验，能够根据用户自己的选择实现多个城市天气数据的展示，并且有着好看的背景图，给予用户不一样的视觉体验，在展示天气数据方面，展示得足够齐全，满足绝大多数用户的需求，生活的建议也比较充足，可以同时对比本地时间和世界时间，加入了手动定位功能，可以根据用户自己的选择，来确定是否需要定位，并展示定位后所在地的天气数据。

***

### 使用到的技术说明：

> - GSON数据解析
> - Okhttp3的网络请求
> - 侧滑
> - 数据库
> - SharedPreferences
> - FloatingActionButton(浮动按钮)
> - 帧布局
> - 动态加载Fragment和ViewPager
> - Glide的使用
> - RecyclerView的下拉刷新和ListView的数据更新
> - 自动定位功能
> - 后台服务自动更新

***

### 收获

> 1.学习了JSON数据的格式，初步了解如何解析JSON数据，解析数据后又如何实现类的封装
> 
> 2.网络请求的基本使用，加深了网络请求的使用
> 
> 3.初步了解MaterialDesign，并且初步认识侧滑，学会简单的侧滑使用
> 
> 4.初步认识数据库，对数据库有了一点的了解
> 
> 5.SharedPreference的使用比较多，使用SharedPreference实现数据的存储，对SharedPreference的认识加深，了解SharedPreference的三种使用方法
> 
> 6.首次使用了FloatingActionButton，对UI的认识再次进一步了解
> 
> 7.虽然使用了帧布局，但对其的了解还不够深
> 
> 8.收获最大的应该是动态加载Fragment，并且实现与ViewPager结合，建立了一个Fragment来实现所有天气展示需要的布局，通过SharedPreference中的数据存储，来实现动态加载Fragment，通过循环实现加载多个所需的天气页面，深刻的认识了Fragment和ViewPager的结合使用，引发了对Fragment中嵌套Fragment的思考
> 
> 9.学习使用Glide加载图片，了解了Glide的简单使用，初步认识Glide，但对Glide的了解还仅仅是表面的，或者说是浅层次的加载图片
> 
> 10.RecyclerView的下拉刷新是之前学习过的，这次再一次的使用，加深了对其的熟练度，但明显不够熟练度额弊端也在这次使用中暴露出来，由于敲的还比较少，大部分代码都是跟着之前的模板敲的，只是本次学习中暴露的最大一个弊端，对知识的掌握的熟练度还远远不够，需要勤加练习
> 
> 11.因为天气要实现基本的定位功能，所以初步学习了自动定位，对定位有一个初步的了解，虽然还没有学习地图，但学习定位打下了一定的基础，有着一定的认识
> 
> 12.服务这一块，虽然实现了后台服务自动更新，但由于是后台，展现的效果不是很明显，所以对其的使用还只停留在书本上的那些简单的认知，这次实现天气的更新自己修改了时间，设置成了4小时更新，但这种自动更新我没能很好的去验证和体现，所以相对来说，服务这一块，欠缺的还很多
> 
> 13.这次学习，很明显学习了很多的新知识，虽然之前看书开始初步了解天气APP的制作，但到了真正做的时候，出现了很多问题，这次的天气制作，其实有很大一部分都是看的书上的，其他功能都是后来慢慢改加进去的，这次也暴露了不少问题，对新知识的掌握还是过于浅显，对之前学习得知识还不够牢固，欠缺很大程度的练习和熟练度，以后还得需要多敲代码。

***

### 截图

![](https://ooo.0o0.ooo/2017/02/23/58aeb8c6c9680.jpg)
![](https://ooo.0o0.ooo/2017/02/23/58aeb8f982bd8.jpg)
![](https://ooo.0o0.ooo/2017/02/23/58aeb9065e946.jpg)
![](https://ooo.0o0.ooo/2017/02/23/58aeb9186f161.jpg)
![](http://jycloud.9uads.com/web/GetObject.aspx?filekey=04afb1ca74fbd60fa59af34ab868c935)
![](https://ooo.0o0.ooo/2017/02/23/58aeb93d62eeb.jpg)
![](http://jycloud.9uads.com/web/GetObject.aspx?filekey=4e081573a848a4c63ebfd697b35cfc49)
![](http://jycloud.9uads.com/web/GetObject.aspx?filekey=8094b9149c5060b922acc9ace6eb63c6)
![](http://jycloud.9uads.com/web/GetObject.aspx?filekey=67c58d55fc3bbf59306cb698c6c1ac4e)
![](http://jycloud.9uads.com/web/GetObject.aspx?filekey=cda77a07e256d38bfd69d055257ebc29)