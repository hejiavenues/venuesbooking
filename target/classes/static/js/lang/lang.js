
var zs_loc=localStorage.getItem('locale');
if(zs_loc==null){
	zs_loc="en";
}
const i18n = new VueI18n({
    locale: zs_loc, // 语言标识
    messages:zs_messages
})