function getDate(value){	
    var d = new Date();
    if(value==null){
    	if(arguments.length >= 2) {
    		d=getDate(arguments[1]);
    		d = new Date( d.setDate(d.getDate()-1));
    	}
    } else {
       	value = value.replace(/^(\d{4})\D?(\d{2})\D?(\d{2})\D?(\d{2})\D?(\d{2})\D?(\d{2})$/,'$1/$2/$3 $4:$5:$6');
       	d.setTime(Date.parse(value));
    }
    return d;
};
function isNotBlank(value){
	if(value == 'undefined') {
		return false;
	}
    if(value.replace(/(^\s*)|(\s*$)/g, "") == ""){
        return false;
    }
    return true;
};
Date.prototype.format = function(format) {
    /*
     * format="yyyy-MM-dd hh:mm:ss";
     */
    var o = {
        "M+" : this.getMonth() + 1,
        "d+" : this.getDate(),
        "h+" : this.getHours(),
        "m+" : this.getMinutes(),
        "s+" : this.getSeconds(),
        "q+" : Math.floor((this.getMonth() + 3) / 3),
        "S" : this.getMilliseconds()
    }

    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
                .substr(4 - RegExp.$1.length));
    }

    for ( var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
                    : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}

Date.prototype.addSeconds = function(seconds) {
	return new Date(this.getTime()+seconds*1000);
}

//获取指定年月的天数
function   getDaysInMonth(year,month){
	month=parseInt(month,10)+1;
	var  temp=new  Date(year,month,0);
	return  temp.getDate();
}
