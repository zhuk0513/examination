/**
 *
 * @param url  访问路径
 * @param params 请求参数
 * @param success 成功回调函数
 * @param timeout
 * @param error
 * @param context 传入回调函数参数
 */
function ajax(url, type,params, success, timeout, error, context) {
	var options = {
		type : type,
		url : url,
		data : params,
		dataType : 'json',
		cache : false,
		success : function(result)
				{
					if(result)
					{
						if(result.code=='timeout')
						{
							alert(result.msg);
							window.top.location.href=result.path +'/login.jsp';
							return;
						}
						else if(result.code=='loginAgain')
						{
							alert(result.msg);
							return;
						}
					}
					if(success)
						success.call(context, result);
				}
	};
	if (error)
		options.error = error;
	else
		options.error = defaultError;
	if (timeout)
		options.timeout = timeout;
	if(context)
		options.context = context;
	jQuery.ajax(options);
}

function defaultError(XMLHttpRequest, textStatus, errorThrown)
{
	var msg = '';
	var status = XMLHttpRequest.status;
	switch(status)
	{
		case 404:
			msg = '对不起，没有找到您要的页面！';
			break;
		case 500:
			msg = '对不起，您请求的服务出错了，请重试或请求其他服务！';
			break;
	}
	if(msg!='')
	{
		alert(msg);
	}
	return false;
}

/**
 *
 * @param url 打开页面地址
 * @param params 页面传参 json格式
 * @param target '_self'在当前页面打开,'_blank'在新页面打开
 */
function goToUrl(basePath,url,params,target){
	// let str=basePath+'/goToUrl?url='+url+'&params='+encodeURI(JSON.stringify(params));
	// switch (target) {
	// 	case '_self':
	// 		window.location.href=str;
	// 		break;
	// 	case '_blank':
	// 		window.open(str);
	// 		break;
	// 	default:
	// 		window.location.href=str;
	// }

	$('#gotoUrlForm').attr('action',basePath+'/goToUrl');
	$('#gotoUrlForm').attr('target',target==undefined?'_self':target);
	$('#url').val(url);
	$('#params').val(JSON.stringify(params));
	$('#gotoUrlForm').submit();
}

function login_timeout() {
	alert('登录超时，请重新登录！');
	var pathname = window.location.pathname, protocol = window.location.protocol, host = window.location.host;
	pathname = pathname.substring(1);
	top.location.href = protocol + '//' + host + '/'
			+ pathname.substring(0, pathname.indexOf('/'))
			+ '/page/login/login.jsp';
}
