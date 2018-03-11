function deleteThumb(obj){
    if (confirm('是否删除图片?')) {
        $(obj).parent().remove(".return-pic");
        return;
    }
}
    
$(function() {
    function addFile(file)
    {
        loading = layer.load({time: 5*1000});
        var panel = $('<div class="return-pic ' + file.id + '"></div>')
        $('<div class="delete hidden" onclick="deleteThumb(this)">删除</div><div class="success hidden"></div>').appendTo(panel);
        uploader.makeThumb(file, function (error, src)
        {
            if (error)
            {
                alert('不能预览');
                return;
            }
            var img = $('<img src="' + src + '" />').appendTo(panel);
        }, 1, 1);
        //在该父容器下面只许存在一张图片
        $('#'+uploader.options.formData.inputname).children().remove();
        panel.appendTo($('#'+uploader.options.formData.inputname));
        $('#'+uploader.options.formData.inputname).show();
        panel.on('click', function ()
        {
            uploader.removeFile(file);
        });
    }
    //判断定义SAVE_DIR，UPLOAD_WIDTH，UPLOAD_HEIGHT，MAX_NUM
        if(typeof(SAVEDIR) == 'undefined'){SAVEDIR='goods'} else{};
        if(typeof(UPLOADWIDTH) == 'undefined'){ UPLOADWIDTH=200} else{};
        if(typeof(UPLOADHEIGHT) == 'undefined'){UPLOADHEIGHT=200} else{ };
        if(typeof(INPUTNAME) == 'undefined'){INPUTNAME=''} else{ };
        if(typeof(MAXNUM) == 'undefined'){ MAXNUM=10} else{};
    var uploader = new WebUploader.create({
        // 选完文件后，是否自动上传。
        auto: true,

        // swf文件路径
        swf: '__PUBLIC__' +'/plugins/webuploader/Uploader.swf',
        formData: {
                savedir: SAVEDIR,
                width:UPLOADWIDTH,//默认为0，如果为0，服务器不压缩图片
                height:UPLOADHEIGHT,
                inputname:INPUTNAME,
        },
        // 文件接收服务端。
        server: '/TP/commodityUploadAction',

        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: { id: '.thumbPicker', multiple: true },

        //开启分块传输。
        //chunked: true,

        // 禁掉上传前压缩功能，因为会手动裁剪。
        //compress: false,

        multiple: true,
        fileNumLimit: MAXNUM,

        fileVal: 'realeaseimg',

        fileSingleSizeLimit: 5*1024 * 1024,

        compress: {
            width: 500,
            height: 500,

            // 图片质量，只有type为`image/jpeg`的时候才有效。
            quality: 90,

            // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
            allowMagnify: false,

            // 是否允许裁剪。
            crop: true,

            // 是否保留头部meta信息。
            preserveHeaders: true,

            // 如果发现压缩后文件大小比原来还大，则使用原来图片
            // 此属性可能会影响图片自动纠正功能
            noCompressIfLarger: false,

            // 单位字节，如果图片大小小于此值，不会采用压缩。
            compressSize: 0
        },
        // 只允许选择图片文件。
        accept: {
            title: 'Images',
            extensions: 'jpg,jpeg,png,gif',
            mimeTypes: 'image/*'
        },
        onError: function (code)
        {
            alert(code);
        }
    });

    uploader.on('fileQueued', function (file)
    {
        addFile(file);
    });
    uploader.on('uploadError', function (file, reason)
    {
        alert('上传失败!'+reason);
    });
    uploader.on('uploadSuccess', function (file, response)
    {
        if (response.name)
        {
            layer.close(loading);
            $('.'+file.id).find('.success').removeClass('hidden');
            $('.'+file.id).find('.delete').removeClass('hidden');
            $('input[name="'+response.inputname+'"]').val(response.savepath + response.savename);
        }
        else
        {
            if(response.error != null)
            {
                alert(response.error);
            }
            uploadfail = true;
        }
    });
    $('.upload_button').click(function ()
    {
        uploader.upload();
    });
    /**
     * 经封装后的函数，用于一个页面不同位置上传多个图片文件
     * 先给某一个div定义好一个input，赋予name属性，上传成功后
     * 将返回的图片路径赋值给有对应name属性的input，即可
     * @param  string SAVEDIR 图片需要保存的文件夹
     * @param  string SAVENAME 需要保存的input的name名称
     * @param  integer UPLOADHEIGHT 需要对该图片进行的裁剪的高度
     * @param  integer UPLOADWIDTH  需要对该图片进行的裁剪的宽度
     * @return string 提示上传成功或者失败
     */
    $('.thumbPicker').click(function(){
        uploader.options.formData.savedir = $(this).attr('SAVEDIR');
        uploader.options.formData.width = $(this).attr('UPLOADWIDTH');
        uploader.options.formData.height = $(this).attr('UPLOADHEIGHT');
        uploader.options.formData.inputname = $(this).attr('INPUTNAME');
        uploader.options.compress.width = $(this).attr('UPLOADWIDTH');
        uploader.options.compress.height = $(this).attr('UPLOADHEIGHT');
    })
});
