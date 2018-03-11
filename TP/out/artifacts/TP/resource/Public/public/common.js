$(function(){
    /**
     * 点击商城顶部的搜索框上面的“店铺”和“商品”时
     * 标识搜索的类型，同时给点击对象增加active类
     */
    $("#search-goods").click(function(){
        $("#search-type").val(1);
        $("#search-goods").addClass('active');
        $("#search-shop").removeClass('active');
        $("#search-input").val("");
        $("#search-input").attr("placeholder","搜索你喜欢的商品吧");
    });
    $("#search-shop").click(function(){
        $("#search-type").val(0);
        $("#search-shop").addClass('active');
        $("#search-goods").removeClass('active');
        $("#search-input").val("");
        $("#search-input").attr("placeholder","搜索你喜欢的店铺吧");
    });

    /**
     * 商城的搜索如果是搜索商品，跳到searchshop
     * 如果搜索的是店铺，跳到searchgoods
     * 这是通过隐藏的input(id为#search-type)来标识，1为商品，0为店铺
     */
    $("#search-btn").click(function () {
        var searchType = $("#search-type").val();
        if(searchType==0){
            window.location = Think.U('Home/shop/queryshop',"keyword="+$.trim($("#search-input").val()));
        }else{
            window.location = Think.U('Home/goods/querygoods',"keyword="+$.trim($("#search-input").val()));
        }
        
    });

    /**
     * 进入到一个店铺的里面，顶部有搜索本店和全站的搜索按钮
     * 如果搜索的是全站，跳转goodsquery页面
     * 如果搜索的是本店，跳转shop/shop页面，get参数中带上shopid
     */
    $('#shop-search').click(function(){//搜本店
        var keyword = $.trim($("#shop-serach-input").val());
        var shopid = $("#shopId").val();
        window.location = Think.U("home/shop/shop","shopid="+shopid+"&"+"keyword="+keyword);
    })

    $('#mall-search').click(function(){//搜全站
        var keyword = $.trim($("#shop-serach-input").val());
        var shopid = $("#shopId").val();
        window.location = Think.U("home/goods/querygoods","keyword="+keyword);
    })
})

/**
 * ajax获取商城的二级分类
 * @param  object obj 传递过来的option对象
 * @return array()  返回json添加到下级select中去
 */
function getMallCate2(obj){
    var cateid = $(obj).val();
    var data_url = Think.U('home/cate/getMallCate');
    $.ajax({
        url: data_url,    //请求的url地址
        dataType: "json",   //返回格式为json
        async: true, //请求是否异步，默认为异步
        data: {'id':cateid},    //参数值
        type: "POST",   //请求方式
        success: function(data) {
            $('#mallCate2 option').remove();
            for(var i=0;i<data.length;i++){
                var option = '<option value='+data[i]['cateid']+'>'+data[i]['catename']+'</option>';
                $('#mallCate2').append(option);
            }
        }
    });
}

/**
 * ajax获取商城的三级分类
 * @param  object obj 传递过来的option对象
 * @return array()  返回json添加到下级select中去
 */
function getMallCate3(obj){
    var cateid = $(obj).val();
    var data_url = Think.U('home/cate/getMallCate');
    $.ajax({
        url: data_url,    //请求的url地址
        dataType: "json",   //返回格式为json
        async: true, //请求是否异步，默认为异步
        data: {'id':cateid},    //参数值
        type: "POST",   //请求方式
        success: function(data) {
            $('#mallCate3 option').remove();
            for(var i=0;i<data.length;i++){
                var option = '<option value='+data[i]['cateid']+'>'+data[i]['catename']+'</option>';
                $('#mallCate3').append(option);
            }
        }
    });
}

/**
 * ajax获取店铺的二级分类
 * @param  object obj 传递过来的option对象
 * @return array()  返回json添加到下级select中去
 */
function getShopCate1(obj){
    var cateid = $(obj).val();
    var data_url = Think.U('home/cate/getShopCate');
    $.ajax({
        url: data_url,    //请求的url地址
        dataType: "json",   //返回格式为json
        async: true, //请求是否异步，默认为异步
        data: {'id':cateid},    //参数值
        type: "POST",   //请求方式
        success: function(data) {
            $('#shopCate2 option').remove();
            for(var i=0;i<data.length;i++){
                var option = '<option value='+data[i]['cateid']+'>'+data[i]['catename']+'</option>';
                $('#shopCate2').append(option);
            }
        }
    });
}

/**
 * ajax获取地区的二级分类
 * @param  object obj 传递过来的option对象
 * @return array()  返回json添加到下级select中去
 */
function getAreaCate2(obj){
    var provinceid = $(obj).val();
    var data_url = Think.U('shop/getcitybypro');
    $.ajax({
        url: data_url,    //请求的url地址
        dataType: "json",   //返回格式为json
        async: true, //请求是否异步，默认为异步
        data: {'provinceid':provinceid},    //参数值
        type: "POST",   //请求方式
        success: function(data) {
            $('#mallArea2 option').remove();
            for(var i=0;i<data.length;i++){
                var option = '<option value='+data[i]['aid']+'>'+data[i]['areaname']+'</option>';
                $('#mallArea2').append(option);
            }
        }
    });
}

/**
 * 删除一个商品
 * @param int id 商品的id
 * @return boolean true-成功，false-失败
 */
function delGoods(id){
    var load = layer.load(0, {time: 10*1000}); //且设定最长等待10秒 
    layer.confirm('确定删除此商品吗，删除不可以恢复！',
        {icon: 3, title:'删除商品'}, 
        function(index){
        $.ajax({
            url: Think.U('home/goods/delgoods'),    //请求的url地址
            dataType: "json",   //返回格式为json
            async: true, //请求是否异步，默认为异步，这也是ajax重要特性
            data: {'id':id},    //参数值
            type: "POST",   //请求方式
            success: function(data) {
                if(data.status == true){
                    layer.close(load);
                    layer.msg('已成功删除该商品！', {icon: 1});
                    location.reload();
                }
                else{
                    layer.close(load);
                    layer.msg('删除失败，请重试或联系技术人员解决。', {icon: 0});
                }
            }
        });
        //layer.close(index);
    });
}

/**
 * 二手模块的对物品进行回复，ajax加载
 * @return 静态添加回复的条目
 * obj.attr('isLogin')==""不存在用户id，意味着没有登录
 */
function saleReply(obj){
    var obj = $(obj);
    if(obj.attr('isLogin')==""){
        layer.msg('同学，请您登录之后再评论', {icon: 0});
        return ;
    }
    var username = $('#commonbtn').attr('my-name');
    var replyTo = obj.attr('to-name');
    $('#commonbtn').attr('to-id',obj.attr('to-id'));
    $('#commonbtn').attr('to-name',obj.attr('to-name'));
    $('#commonbtn').attr('pid',obj.attr('pid'));
    var preFix = username+" 回复 "+replyTo+" ：";
    $('#reply-to-tips').text(preFix);
}

function saleReplyPost(obj){
    var loading = layer.load(0,{time: 3*1000});
    var obj = $(obj);
    var goodsId = obj.attr('goodsid-id');
    var username = obj.attr('my-name');
    var replyTo = obj.attr('to-name');
    var toId = obj.attr('to-id');
    var parentId = obj.attr('pid');
    var postContent = $('#commoncontent').text();
    if(postContent==""){
        layer.msg('您不能什么都不写呃！', {icon: 0});
        return ;
    }
    $('#commoncontent').text("");
    var object = {};
        object.toid = toId;
        object.pid = parentId;
        object.goodsid = goodsId;
        object.content = postContent;
    $.ajax({
            url: Think.U('home/sale/appraise'),    //请求的url地址
            dataType: "json",   //返回格式为json
            async: true, //请求是否异步，默认为异步，这也是ajax重要特性
            data: object,    //参数值
            type: "POST",   //请求方式
            success: function(data) {
                if(data.status == true){
                    layer.close(loading);
                    var imgSrc = $('#commoncontent').prev('img').attr('src');
                    var date = new Date();
                    var dataTime = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
                    var text = '<li class="list-item clearfix"><div class="head"><img src="'+imgSrc;
                        text = text+'"></div><div class="text"><div class="user clearfix"><div class="username">'+username;
                        text = text+'<span class="reply">回复</span>'+replyTo;
                        text = text+'</div><div class="info"><span class="reply-time">'+dataTime;
                        text = text+'</span><span onclick="saleReply(this)" class="reply" to-name="'+username;
                        text = text+'" to-id="'+toId;
                        text = text+'" pid="'+data.pid;
                        text = text+'" isLogin="1" >回复</span></div></div><p class="detail">'+postContent;
                        text = text+'</p></div></li>';
                    $('#reply-list').append(text);
                    layer.msg('评论成功！', {icon: 1});
                }
                else{
                    layer.msg('评论失败，请重试！', {icon: 0});
                }
            }
        });
}

/**
 * 获取回复的内容
 * 获得textarea的内容，去掉估计的前缀内容
 * 去掉的是如“张三 回复 李四：”这段前缀
 * @return string
 */
function getReplyStr(content, preFix){
    length = preFix.length;
    return content.substr(length);
} 

/**
 * 在评论的页面，单击“展开更多精彩评论”按钮
 * 展开其余的隐藏起来的评论，隐藏评论的原因是
 * 如果有太多评论的话，页面会拉的很长，更多的
 * 评论放在class为other-appraise的元素里面
 */
function spreadAppraise(obj){
    $('.other-appraise').toggle();
    if($('.other-appraise').is(':hidden')){
        $(obj).text('展开更多的精彩评论');
    }
    else{
        $(obj).text('收起更多的精彩评论');
    }
}

/**
 * 切换城市，ajax递交城市id，后台进行相关设置
 * 再用location.href返回首页，让首页去识别后台设置的城市
 * 为什么不在页面直接用a链接，这是可以的，但是为了更好的
 * 后台设置原因，所以用ajax提交再跳转
 * @param int areaId2 城市的id
 */
function changeCity(areaId2){
    areaId2 = (areaId2>0)?areaId2:$("#cityId").val();

    jQuery.post( Think.U('Home/Index/reChangeCity') ,{"city":areaId2,"changeCity":true},function(data) {
        var currurl = location.href;
            currurl = currurl.toLowerCase();
        if(currurl.indexOf("changecity")!=-1){
            location.href = Think.U('home/shop/index');
        }else{
            location.href = location.href;
        }
    });
}

/**
 * 搜索物品的列表
 * 来源可能是顶部的搜索框直接搜索
 * 或者查找到商品的列表之后，点击价格，时间，点击量之类的排序
 * @param obj query对象
 * @param int type 用户识别点击对象的数字
 */
function queryGoods(obj, type){
    var params = {};
    $('#keyWord').val()==""?"":params.keyword=$('#keyWord').val();
    $('#shopId').val()==""?"":params.shopid=$('#shopId').val();
    $('#shopCate2').val()==""?"":params.shopcate2=$('#shopId').val();
    $('#shopCate1').val()==""?"":params.shopcate1=$('#shopCate1').val();
    $('#mallCateId3').val()==""?"":params.mallcate3=$('#mallCateId3').val();
    $('#mallCateId2').val()==""?"":params.mallcate2=$('#mallCateId2').val();
    $('#mallCateId1').val()==""?"":params.mallcate1=$('#mallCateId1').val();
    if(type==0){//综合排序
        params.msort=0;
    }
    if(type==1){
        var sj=$.trim($(obj).attr('data-type'));
        params.msort=sj=="1"?1:2;
    }
    if(type==2){
        var sj=$.trim($(obj).attr('data-type'));
        params.msort=sj=="1"?3:4;
    }
    if(type==3){
        var sj=$.trim($(obj).attr('data-type'));
        params.msort=sj=="1"?5:6;
    }
    if(type==4){
        var price = $("#sprice").val()+"_"+$("#eprice").val();
        params.price = prices;
        params.msort = 7;
    }
    params.Model = "home";
    if(params.shopid>0){//如果是在店铺内进行搜索
        params.Control = "shop";
        params.Action = "shop";
    }
    else{//如果是在商城搜索
        params.Control = "goods";
        params.Action = "querygoods";
    }
    $.post(Think.U('home/index/geturl') ,params,function(data) {
        location.href = data.url;
    });
}

/**
 * 商城模块对商品进行回复
 * 点击回复按钮，显示出对应的评论输入框
 */
function mallRepley(obj){
    var obj = $(obj);
    if(obj.attr('isLogin')==""){
        layer.msg('同学，请您登录之后再评论', {icon: 0});
        return ;
    }
    var pid = obj.attr("pid");
    $("#comment-"+pid).removeClass('content-post');
    $(".content-post").hide(0);
    $("#comment-"+pid).toggle(100);
    $("#comment-"+pid).addClass('content-post');
}

/**
 * 提交商城的商品评论内容
 * ajax会返回评论者的用户名，被评论者的用户名，以及该条评论的id
 */
function mallRepleyPost(obj){
    var loading = layer.load(0,{time: 3*1000});
    var obj = $(obj);
    if(obj.attr('isLogin')==""){
        layer.msg('同学，请您登录之后再评论', {icon: 0});
        return ;
    }
    var goodsId = obj.attr('goods-id');
    var toId = obj.attr('to-userid');
    var parentId = obj.attr('pid');
    var postContent = obj.prev('.commoncontent').val();
    if(postContent==""){
        layer.msg('您不能什么都不写呃！', {icon: 0});
        return ;
    }
    var isChild = obj.attr('is-child');
    obj.prev('.commoncontent').val("");
    obj.parents(".content-post").hide();
    var object = {};
        object.toid = toId;
        object.pid = parentId;
        object.goodsid = goodsId;
        object.content = postContent;
    $.ajax({
        url: Think.U('home/goods/appraise'),    //请求的url地址
        data: object,    //参数值
        success: function(data) {
            if(data.status == false){
                layer.msg('评论失败，请重试！', {icon: 0});
            }
            else{
                layer.close(loading);
                var date = new Date();
                var dateTime = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
                var text = '<div class="child-item clearfix"><p class="comment-top clearfix"><span class="name">'+data.user.username;
                    text = text+'<span class="to">回复</span>'+data.touser.username;
                    text = text+'</span><span class="info"><span class="date">'+dateTime;
                    text = text+'</span><span pid="'+data.pid+'" class="reply" onclick="mallRepley(this)" isLogin="1">回复</span></span></p><p class="content">';                    
                    text = text+postContent+'</p><div id="comment-'+data.pid;
                    text = text+'" class="content-post clearfix" style="display: none;"><input placeholder="写下你的精彩评论吧！" contenteditable="true" class="commoncontent"><div  is-child="true" pid="';
                    text = text+data.pid+'" goods-id="'+goodsId+'" to-userid="'+data.touser.uid;
                    text = text+'" class="comment-btn" onclick="mallRepleyPost(this)">提交评论</div></div></div>';
                    if(isChild){//如果是对子级评论回复
                        var desc = obj.parents('.child-item');
                        desc.after(text);
                    }
                    else{//如果是对顶级评论回复
                        var desc = obj.parents('.parent').next(".child");
                        desc.append(text);
                    }
                layer.msg('评论成功！', {icon: 1});
            }
        }
    });
}

/**
 * 商城商品的第一个评论者，与对楼回复不同，故用另一个函数
 * ajax会返回评论者的用户名，被评论者的用户名，以及该条评论的id
 */
function mallFirstRepleyPost(obj){
    var loading = layer.load(0,{time: 3*1000});
    var obj = $(obj);
    if(obj.attr('isLogin')==""){
        layer.msg('同学，请您登录之后再评论', {icon: 0});
        return ;
    }
    var goodsId = obj.attr('goods-id');
    var toId = obj.attr('to-userid');
    var parentId = obj.attr('pid');
    var postContent = obj.prev('.commoncontent').val();
    if(postContent==""){
        layer.msg('您不能什么都不写呃！', {icon: 0});
        return ;
    }
    var isChild = obj.attr('is-child');
    obj.prev('.commoncontent').val("");
    obj.parents(".content-post").hide();
    var object = {};
        object.toid = toId;
        object.pid = parentId;
        object.goodsid = goodsId;
        object.content = postContent;
    $.ajax({
        url: Think.U('home/goods/appraise'),    //请求的url地址
        data: object,    //参数值
        success: function(data) {
            if(data.status == false){
                layer.msg('评论失败，请重试！', {icon: 0});
            }
            else{
                layer.close(loading);
                var date = new Date();
                var dateTime = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
                $('.no-appraise').remove();
                var text = '<div class="comment-wrap clearfix"><div class="parent clearfix"><p class="comment-top clearfix"><span class="name">'+data.user.username;
                    text = text+'</span><span class="info"><span class="date">'+dateTime;
                    text = text+'</span><span pid="'+data.pid+'" class="reply" onclick="mallRepley(this)">回复</span></span></p><p class="content">';
                    text = text+postContent+'</p><div id="comment-'+data.pid;
                    text = text+'" class="content-post clearfix" style="display: none;"><input placeholder="写下你的精彩评论吧！" contenteditable="true" class="commoncontent"><div pid="';
                    text = text+data.pid+'" goods-id="'+goodsId+'" to-userid="'+data.touser.uid;
                    text = text+'" class="comment-btn" onclick="mallRepleyPost(this)">提交评论</div></div></div><div class="child clearfix"></div></div>';
                    $('.appraise-panel').prepend(text);
                //跳到评论框的顶部   
                if($('.com-top').offset()){
                    $('html,body').animate({ scrollTop: $('.com-top').offset().top-20 }, 1000,'swing');
                }
                layer.msg('评论成功！', {icon: 1});
            }
        }
    });
}

/**
 * 在二手模块对一个物品进行收藏
 * @param int id 物品的id
 * @return false-提示失败，true-提示成功
 */
function saleFavor(obj){
    var obj = $(obj);
    var isLogin = obj.attr('isLogin');
    if(!isLogin){
        layer.msg('没登录是无法收藏的哟！', {icon: 0});
        return ;
    }
    var loading = layer.load(0,{time: 3*1000});
    $.ajax({
        url: Think.U('home/sale/favor'),    //请求的url地址
        data: {'id':obj.attr('data-id')},    //参数值
        success: function(data) {
            layer.close(loading);
            if(data.status==true){
                layer.msg('收藏成功，您可以在个人中心查看您的收藏。', {icon: 1});
                return;
            }
            if(data.status==-1){
                layer.msg('没登录是无法收藏的哟！', {icon: 0});
            }
            else{
                layer.msg('发生发错，收藏失败！', {icon: 0});
            }
        }
    })
}

/**
 * 在二手模块对一个物品进行举报
 * @param int id 物品的id
 * @return false-提示失败，true-提示成功
 */
function saleReport(id){
    var loading = layer.load(0,{time: 3*1000});
    $.ajax({
        url: Think.U('home/sale/report'),    //请求的url地址
        data: {'id':id},    //参数值
        success: function(data) {
            layer.close(loading);
            if(data.status==true){
                layer.msg('举报成功，我们会迅速处理的！', {icon: 1});
            }
            else{
                layer.msg('举报失败，请重试或者联系管理员处理！', {icon: 0});
            }
        }
    })
}

/**
 * 在商城对一个商品进行收藏
 * @param int id 商品的id
 * @return false-提示失败，true-提示成功
 */
function goodsFavor(obj){
    var obj = $(obj);
    var isLogin = obj.attr('isLogin');
    if(!isLogin){
        layer.msg('没登录是无法收藏的哟！', {icon: 0});
        return ;
    }
    var loading = layer.load(0,{time: 3*1000});
    $.ajax({
        url: Think.U('home/goods/favor'),    //请求的url地址
        data: {'id':obj.attr('data-id')},    //参数值
        success: function(data) {
            layer.close(loading);
            if(data.status==true){
                layer.msg('收藏成功，您可以在个人中心查看您的收藏。', {icon: 1});
            }
            else{
                layer.msg('没登录是无法收藏的哟！', {icon: 0});
            }
        }
    })
}

/**
 * 在二手模块对一个物品进行举报
 * @param int id 物品的id
 * @return false-提示失败，true-提示成功
 */
function goodsReport(id){
    var loading = layer.load(0,{time: 3*1000});
    $.ajax({
        url: Think.U('home/goods/report'),    //请求的url地址
        data: {'id':id},    //参数值
        success: function(data) {
            layer.close(loading);
            if(data.status==true){
                layer.msg('举报成功，我们会迅速处理的！', {icon: 1});
            }
            else{
                layer.msg('举报失败，请重试或者联系管理员处理！', {icon: 0});
            }
        }
    })
}

/**
 * 在商城对一个店铺进行收藏
 * @param int id 商品的id
 * @return false-提示失败，true-提示成功
 */
function shopFavor(obj){
    var obj = $(obj);
    var isLogin = obj.attr('isLogin');
    if(!isLogin){
        layer.msg('没登录是无法收藏的哟！', {icon: 0});
        return ;
    }
    var loading = layer.load(0,{time: 3*1000});
    $.ajax({
        url: Think.U('home/shop/favor'),    //请求的url地址
        data: {'id':obj.attr('data-id')},    //参数值
        success: function(data) {
            layer.close(loading);
            if(data.status==true){
                layer.msg('收藏成功，您可以在个人中心查看您的收藏。', {icon: 1});
            }
            else{
                layer.msg('没登录是无法收藏的哟！', {icon: 0});
            }
        }
    })
}

/**
 * 卖家把消息标为已读
 */
function salerReadMsg(obj){
    var obj=$(obj);
    var loading = layer.load(0,{time: 3*1000});
    $.ajax({
        url: Think.U('home/shop/readmsg'),    //请求的url地址
        data: {'id':obj.attr('data-id')},    //参数值
        success: function(data) {
            layer.close(loading);
            if(data.status==true){
                layer.msg('成功标为已读。', {icon: 1});
                obj.parents(".item").hide(300);
            }
            else{
                layer.msg('设置失败，请联系客服解决问题！', {icon: 0});
            }
        }
    })
}

function salerReply(obj){
    var obj = $(obj);
    obj.parent(".other").next(".common-hide").removeClass('common-hide');
    $(".common-hide").hide(0);
    obj.parent(".other").next(".common-target").addClass('common-hide');
    obj.parent(".other").next(".common-hide").toggle(100);
}

function salerPost(obj){
    var loading = loading = layer.load(0,{time: 2*1000});
    var obj=$(obj);
    var params = {};
    var username = obj.attr('username');
    var userid = obj.attr('userid');
    var goodsid = obj.attr('goods-id');
    var pid = obj.attr('pid');
    var commonPost = obj.prev(".c-input").val();
    var salername = obj.attr("salername");
    obj.parent().hide();
    obj.prev(".c-input").val("");
    params.touserid = userid;
    params.goodsid = goodsid;
    params.content = commonPost;
    params.pid = pid;
    $.ajax({
        url: Think.U('home/goods/salerappraise'),    //请求的url地址
        data: params,    //参数值
        success: function(data) {
            layer.close(loading);
            if(data.status==true){
                var text = '<div class="text"><span class="n">'+salername;
                    text = text+'</span><span class="r">回复</span><span class="n">'+username;
                    text = text+'</span><span class="content">&nbsp;&nbsp;'+commonPost;
                    text = text+'</span></div>';
                var desc = obj.parent(".common-hide").prev().prev(".common-box");
                desc.append(text);
                layer.msg('成功追加了评论！', {icon: 1});
            }
            else{
                layer.msg('追加评论失败，请联系客服解决问题！', {icon: 0});
            }
        }   
    })
}

function salerReadAppraise(obj){
    var loading = loading = layer.load(0,{time: 2*1000});
    obj = $(obj)
    var id =obj.attr("data-id");
    $.ajax({
        url: Think.U('home/goods/readappraise'),    //请求的url地址
        data: {'id': id},    //参数值
        success: function(data) {
            layer.close(loading);
            if(data.status==true){
                obj.parents(".item").hide(300);
                layer.msg('成功把评论标为了已读状态！', {icon: 1});
            }
            else{
                layer.msg('标为已读评论失败，请联系客服解决问题！', {icon: 0});
            }
        }   
    })
}

   

    
