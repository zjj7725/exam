# exam
  经过在红岩这7个月的学习，我收益很大。我感觉就是在分导师之后，有了导师的解惑与督促，我学习的状态和效果很好。按照学习路线基本上学一个点就写一个Demo，不懂的就问唐大，弄懂了很多东西，感觉很好。
  在考核之前，我甚至心里有些低了。但是考核时，我发现就像唐大说的，我看的东西还是太少了。有一部分考核内容我都没了解过，都是这次临时看的。
  我没做出来的最先就是ActionBar，我一直用的是最新的API，其中对ActionBar认为是比较过时的，而且占空间，所以我也就没太在意，结果这次，在ActionBar上就好了许多时间。然后就是点击ListView中的item显示大图和显示段子详情没有做出来，我想的是点击后就用Intent携带Bundle把Bitmap和String传过去但是我弄了很久还是不能通过Handler或者回调实现。最后就是点击overflow中的APP更新解析XML并安装apk包，但是由于之前找API练习解析时没有找到使用XML解析的，所以我的XML解析还停留在书本上的实例代码，对于这次似乎有嵌套的XML，我查了一些，但是奈何时间不够，没能解析出来。
  接下来是我做出来的，ActionBar是边看边做的，我没有使用ActionBar中的Tab导航，我是使用的自己用三个TextView来实现同样的Tab效果。我实现了Fragment+ViewPager，写了很多ListView。使用AsyncTask解决了从网上请求数据，并动态加载到ListView中的问题。其中还使用了List<Map<String, Object>>用来装请求到的数据，然后通过继承了BaseAdapter的Adapter向ListView中的item填充数据。最后是第三个页面，我写了一个聊天界面。我首先自己制作了Nine-Patch图片，虽然很丑。然后通过LIstView写出了整个界面，实现了消息的收发。
  对于这次考核，我觉得我做的不够好，不过这次考核给了我很多。我清楚了自己和他人的差距，我清楚了自己看的东西还太少，我两天也只睡了几个小时，我体会到了编程的奥妙，或许这还不算编程吧。不管，我这次过没过，我都不会气馁，毕竟都是成年人了。但是我还是想留下来的，因为红岩教会了我Stack Overflow的精神：自己不付出相应的努力而提出的问题，大家也没有义务去回答。教会了我独立去思考，自己去百度、谷歌。总之，我想要感谢红岩，不愧为重邮第一。
  
