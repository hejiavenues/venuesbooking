//非vue得commen.js form.js,layui.js以及其他基于juery的插件的通用字典
const zs_commen_messages = {
    zh: {
    "bookstrap-table-lang-path":"../../plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js",
    },
    en: {
    "bookstrap-table-lang-path":"../../plugins/bootstrap-table/locale/bootstrap-table-en-US.min.js",
    '您的登陆已经失效，请您重新登陆':"Your login has expired. Please re-login!",
    "系统提示":"System Tips",
    '确认':"ok",
    '确定':"sure",
    "取消":'cancel',
    '您没有选中任何数据项！':"You did not select any data !",
    '您只能选择一条数据项！':"You can only select one data item!",
    "请输入有效金额":"Please enter the valid amount.",
    '系统窗口':"System window",
    "请求超时，请稍候重试...":"Request timeout, please try again later",
    "注：您确定要删除吗？该操作将无法恢复":"Are you sure you want to delete it? This operation will not be restored",
    "您确定要保存当前数据项修改操作吗？":"Are you sure you want to save the currently modified data? ",
    "注：您确定要安全退出本次登录吗？":"Are you sure you want to exit this login safely?",
    "修改密码":"change password",
    "原密码为空!":"The original password is empty",
    "新密码为空!":"The new password is empty",
    
    },
    id:{

        '您的登陆已经失效，请您重新登陆':"Log masuk anda telah dinonaktifkan, silakan masuk lagi",
        "系统提示":"Sistem petunjuk",
        '确认':"konfirmasi",
        '确定':"yakin",
        "取消":'batalkan',
        '您没有选中任何数据项！':"Anda tidak memilih entri data apapun!",
        '您只能选择一条数据项！':"Anda hanya dapat memilih satu item data!",
        "请输入有效金额":"Masukkan jumlah yang sah",
        '系统窗口':"Sistem jendela",
        "请求超时，请稍候重试...":"Waktu habis, silahkan coba lagi …",
        "注：您确定要删除吗？该操作将无法恢复":"Catatan: anda yakin ingin menghapusnya? Operasi ini tidak akan bisa dipulihkan",
        "您确定要保存当前数据项修改操作吗？":"Anda yakin ingin menyimpan aksi modifikasi item data saat ini? ",
        "注：您确定要安全退出本次登录吗？":"Catatan: anda yakin ingin keluar dengan aman dari login ini?",
        "修改密码":"Mengganti kata sandinya",
        "原密码为空!":"Kode asli kosong!",
        "新密码为空!":"Password baru kosong!",
        
        }
}
/**
 * layui字典(laydate,laydate,layer..)
 */
const zs_layui_messages = {
    zh: {
    	layDate:"cn"//laydate语言
    },
    en: {
    	layDate:"en",
    	//layTable+++++++++
    	"无数据":"No data",
    	"筛选列":"Filter columns",
    	"导出":"export",
    	"返回的数据不符合规范，正确的成功状态码 (":"The data returned does not conform to the specifications and the correct success status code (",
    	") 应为：":"should be",
    	'">数据接口请求异常：':'">Data interface request exception：',
    	"导出功能不支持 IE，请用 Chrome 等高级浏览器导出":"Export function does not support IE, please use advanced browsers such as Chrome to export",
    	 '<li data-type="csv">导出到 Csv 文件</li>': '<li data-type="csv">Export to Csv file</li>',
    	 '<li data-type="xls">导出到 Excel 文件</li>':'<li data-type="csv">Export to Excel file</li>',
    	 '<span class="layui-laypage-count">共 ':'<span class="layui-laypage-count">total ',
    	 " 条</span>":"rows</span>",
    	 " 条/页</option>":"rows/page",
    	 "确定":"confirm",
    	 '&#x5230;&#x7B2C;':"to",
    	 '&#x9875;<button type="button" class="layui-laypage-btn">&#x786e;&#x5b9a;</button>':
    		 'page<button type="button" class="layui-laypage-btn">confirm</button>'

    },
    id: {
    	layDate:"en",
    	//layTable+++++++++
    	"无数据":"Menurut tak terhitung jumlahnya",
    	"筛选列":"Memeriksa daftar",
    	"导出":"Ekspor penanda",
    	"返回的数据不符合规范，正确的成功状态码 (":"Data yang dikembalikan tidak sesuai dengan spesifikasi, kode status yang benar(",
    	") 应为：":"karena",
    	'">数据接口请求异常：':'">Antarmuka data meminta pengecualian：',
    	"导出功能不支持 IE，请用 Chrome 等高级浏览器导出":"Eksport tidak mendukung IE, silakan ekspor dengan peramban lanjutan seperti Chrome",
    	 '<li data-type="csv">导出到 Csv 文件</li>': '<li data-type="csv">Diekspor ke file Csv</li>',
    	 '<li data-type="xls">导出到 Excel 文件</li>':'<li data-type="csv">Diekspor ke berkas Excel</li>',
    	 '<span class="layui-laypage-count">共 ':'<span class="layui-laypage-count">Terdiri dari ',
    	 " 条</span>":"jalan</span>",
    	 " 条/页</option>":"jalan/halaman",
    	 "确定":"yakin",
    	 '&#x5230;&#x7B2C;':"to",
    	 '&#x9875;<button type="button" class="layui-laypage-btn">&#x786e;&#x5b9a;</button>':
    		 'page<button type="button" class="layui-laypage-btn">confirm</button>'

    }
}




//設置語言

if(localStorage.getItem('locale')==null){
	localStorage.setItem('locale','zh');
}

const zs_lang_locale=localStorage.getItem('locale');
const zsl=function(key){
	let la=zs_commen_messages[zs_lang_locale];
	/*if(la=='zh'){
	   return key	;
	}*/
	if(la[key]==undefined){
		return key;
	}
	return la[key];
};
const zsly=function(key,arr){
	let la=zs_layui_messages[zs_lang_locale];
	if(arr==undefined){
		if(la[key]==undefined){
			return key;
		}
		return la[key];
	}
	if(arr=="arr"){
		var rarr=new Array();
		for(var i=0;i<key.length;i++){
			var keyi=key[i];
			if(la[keyi]==undefined){
				return keyi;
			}
			rarr.push(la[keyi]);
		}
		return rarr;
	}

};


