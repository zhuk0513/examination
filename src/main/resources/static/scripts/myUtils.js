/***
 * UUID
 * ***/
function getUUID() {
    var s = [];
    var hexDigits = "0123456789abcdef";
    for (var i = 0; i < 36; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
    }
    s[14] = "4";
    s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);
//    s[8] = s[13] = s[18] = s[23] = "-";

    var uuid = s.join("");
    return uuid;
}



/**
 *  elment ui消息提醒
 * @param app  vue对象
 * @param content  提醒内容
 * @param type  success/warning/info/error
 * @param duration  默认3000毫秒
 */
function elMessage(app,content,type,duration){
    duration=duration?duration:3000;
    type=type?type:'info';
    app.$message({
        message: content,
        type: type,
        duration:duration
    });
}

/**
 *  elment ui  通知
 * @param app  vue对象
 * @param content  通知内容
 * @param title  标题
 */
function elNotice(app,content,title){
    title=title?title:'';
    app.$notify({
        message: content,
        title: title
    });
}

function elDelMessage(app,content,fuc,param,title){
    title=title?title:'提示';
    vb.$confirm(content, title, {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
    }).then(() => {
        fuc.call(param);
        this.$message({
            type: 'success',
            message: '删除成功!'
        });
    }).catch(() => {
        this.$message({
            type: 'info',
            message: '已取消删除'
        });
    });
}

function   tableDateFormatter(row,col){
    let time=row[col.property];
    return  dateFormatter(time);
}

function   dateFormatter(time){
    if(time!=''&&time!=undefined){
        time=new Date(parseInt(time)).format("yyyy-MM-dd hh:mm:ss");
    }
    return  time;
}

/**
 * 随机生产颜色
 * @returns RGBA
 */
function  randomRgbaColor(){
    let r=Math.floor(Math.random()*256);
    let g=Math.floor(Math.random()*256);
    let b=Math.floor(Math.random()*256);
    let a=Math.floor(Math.random()*256);
    return "rgb("+r+','+g+','+b+','+a+")";
}

/**
 * 随机生产颜色
 * @returns 十六进制格式颜色
 */
function randomHexColor(){
    return '#'+('00000'+(Math.random()*0x1000000<<0).toString(16)).substr(-6);
}


/**
 * 格式化日期方法
 * 已注入所有Vue实例，
 * template模板里调用 $dateFormat(date)
 * 组件方法里调用 this.$dateFormat(date)
 * 例：this.$dateFormat('Dec 27, 2017 3:18:14 PM') 得到 '2017-12-27 15:18:14'
 */

function  dateFormat(date) {
    if (!date) return ''
    let date_format = new Date(date)
    let year = date_format.getFullYear()
    let month = date_format.getMonth() + 1
    if (month < 10) month = '0' + month
    let mydate = date_format.getDate()
    if (mydate < 10) mydate = '0' + mydate
    let hours = date_format.getHours()
    if (hours < 10) hours = '0' + hours
    let minutes = date_format.getMinutes()
    if (minutes < 10) minutes = '0' + minutes
    let seconds = date_format.getSeconds()
    if (seconds < 10) seconds = '0' + seconds
    let time = `${year}-${month}-${mydate} ${hours}:${minutes}:${seconds}`
    return time
}
