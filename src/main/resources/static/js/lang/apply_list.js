/**
 * 审批列表国际化
 */

const zs_messages = {
    zh: {
    	bt:{//常用按钮
    		load:"查询",
    		reload:"刷新",
    		assign:"分配",
    		transferCase:"调案",
    		decrypt:"脱敏",
    		details:"审批",
    		detailsBatch:"批量审批"
    	},
    	//一般
    	applyCode: '申请单号',
    	name:"客户姓名",
    	mobile:"手机号码",
    	idNumber:"身份证号",
    	decisionResult:"审批结果",
    	type:"子渠道",
        channel:"详细子渠道",
	    product:"产品",
	    thenew:"最新详情",
	    subCase:"渠道",
	    subChannel:"详细子渠道",
	 
	    //列表类
	    list:{
	    	   decisionState:"审批状态",
	    	 "applyCode": "申请单号", 
			 "userName": "客户姓名", 
			 "userMobile":"手机号码",
			 "idNumber":"证件号码",
			 "productId" : "产品",
		     "subCase" : "子渠道",
		     "subChannelDesc": "详细子渠道",
			 "applicationAmount": "借款金额",
			 "gmtCreate" : "进件时间", 
			 "gmtDecision" : "审批时间",
			 "userNameDecision" : "审批用户",
			 "decisionResult": "审批结果",
			 "channelName" : "渠道", 
			 "queueNo": "当前队列", 
			 "chnName":"审批人员",
			 "decisionResultDesc":"审批备注",
			 newChnName:"推送至",
			 nowQueue:"推送至队列",
			 nowResultName:"最新后决策结果",
			 msg:"审批信息",
	    },
	    //select
	    select:{
	    	
	    	v9:"等待一级审批",
	    	v10:"等待二级审批",
	    	v8:"等待分配",
	    	v0:"审批通过",
	    	v1:"审批拒绝",
	    	vall:"全部",
	    	vq:"其他",
   	    	own:"自有",
   	    	channel:"渠道",
   	    	other:"其他",
   	    	
	    },
	    //状态
	    states:{
	    	
	    	v9:"等待一级审批",
	    	v10:"等待二级审批",
	    	v8:"等待分配",
	    	v0:"审批通过",
	    	v1:"审批拒绝",
	    	v11:"一级审批拒绝",
   	    	v12:"二级审批拒绝",
   	    	v10:"通过一级审批",
   	    	notcan:	"不可审批",
	    },
	    //提示
	    msg:{
	    	msg0:"您没有选中任何数据项",
	    	msg1:'只能选择审批结果为[等待分配]的数据！',
	    	msg2:'只能选则相同队列！',
	    	msg3:'只能选则同一渠道！',
	    	msg10:"请选择调度人员",
	    	msg11:"调度人员相同",
	    	msg12:'您确定要执行调案？当前状态为',	
	    	msg13:"调案全部",
	    	msg14:"调案部分",
	    	msg21:"您没有选中待审批数据！",
	    	msg22:'您确定要批量审批通过待审批',
	    	msg23:'条记录？',
	    },
	    //分配页面
	    assign:{
	    	td:"电核人员",
	    	td2:"二级审批",
	    	channel:"渠道"
	    },
	    //调案页面
	    allocation:{
	    	before:"被调电核",
	        after:"接收电核",
	        select:"请选择",
	    },
	    //隊列頁面
	    qe:{
	    	channel:"渠道",
	    	product:"产品",
	    	queue1 :"一级电核队列",
	    	queue2:"二级审批队列",
	    	username:"账号",
	    	chnName:"用户名",
	    	allProduct:"渠道下所有产品",
	    	addQueue1:"增加一级队列",
	    	addQueue2:"增加二级队里",
	    	productCode:"负责产品",
	    	queue:"审批队列",
	 
	    },
	    //批量审批页面
	    batch:{
	    	waitAppr:"待审批",
	    	batchAppr:"等待审批",
	    	
	    }
	    
	    
    },
    en: {
    	bt:{//常用
    		load:"query",
    		reload:"refresh",
    		assign:"allocation",
    		transferCase:"redistribution",
    		decrypt:"encryption",
    		details:"approval",
    		detailsBatch:"batch approval"
    	},
    	applyCode: 'order',
    	userName:"name",
    	mobile:"phone",
    	idNumber:"ID card",
    	decisionResult:"result",
    	type:"type",
    	channel:"channel",
    	product:"product",
	    thenew:"(latest details)",
	    subCase:"type",
	    subChannel:"channel",
	    

       select:{
   	    	v9:"waiting for first approval",
   	    	v10:"Waiting for second-level approval",
   	    	v8:"Waiting for allocation",
   	    	v0:"through approval",
   	    	v1:"refuse",
   	    	vall:"all",
   	    	vq:"other",
   	    	other:"other",
   	    	own:"own",
   	    	channel:"other",
   	    },
   	    states:{
	    	
	    	v9:"wait 1",
	    	v10:"wait 2",
	    	v8:"wait allocation",
	    	v0:"success",
	    	v1:"fail",
	    	v11:"fail 1",
   	    	v12:"fail 2",
   	    	v10:"success 1",
   	    	notcan:	"approval is not allowed",

	    },
        list:{
    	    decisionState:"decisionState",
    	    msg:"msg",
	    	 "applyCode": "order", 
			 "userName": "name", 
			 "userMobile":"phone", 
			 "idNumber":"ID card",
			 "productId" : "product",
		     "subCase" : "type",
		     "subChannelDesc": "channel",
			 "applicationAmount": "loan amount",
			 "gmtCreate" : "entryTime", 
			 "gmtDecision" : "approvalTime",
			 "userNameDecision" : "approval user",
			 "decisionResult": "result",
			 "channelName" : "channel name", 
			 "queueNo": "queue", 
			 "chnName":"Approver",
			 "decisionResultDesc":"Remarks",
			 newChnName:"newestApprover",
			 nowQueue:"newestQueue",
			 nowResultName:"newestResult",
	    },
	    msg:{
	    	msg0:"You did not select any data items!",
	    	msg1:'Only data with approval results [waiting for allocation] can be selected!',
	    	msg2:'Only the same queue can be selected！',
	    	msg3:'Only the same channel can be chosen！',
	    	msg10:"Please select dispatcher!",
	    	msg11:"The dispatcher is the same!",
	    	msg12:'Are you sure you want to carry out the investigation? The current state is',	
	    	msg13:"redistribution all",
	    	msg14:"redistribution part",
	    	msg21:"您没有选中待审批数据！",
	    	msg21:"You did not select the data to be approved!",
	    	msg22:'您确定要批量审批通过待审批',
	    	msg22:'You confirm the batch pass',
	    	msg23:'条记录？',
	    	msg23:'records ?',
	    },
	    //调案页面
	    allocation:{
	    	before:"before",
	        after:"after",
	        select:"select",
	    },
	    assign:{
	    	td:"first level",
	    	td2:"two level ",
	    	channel:"channel"
	    },
	    //隊列頁面
	    qe:{
	    	channel:"channel",
	    	product:"product",
	    	queue1 :"first queue",
	    	queue2:"second queue",
	    	username:"account",
	    	chnName:"userName",
	    	allProduct:"all",
	    	addQueue1:"add first queue",
	    	addQueue2:"add second queue",
	    	productCode:"product",
	    	queue:"queue",
	    },
	    //批量审批页面
	    batch:{
	    	waitAppr:"Pending approval",
	    	batchAppr:"Batch approval",
	    }
    },
    id: {
    	bt:{//常用按钮
    		load:"periksa",
    		reload:"refresh",
    		assign:"Pada masa kelegaan",
    		transferCase:"Disetel.",
    		decrypt:"desensitisasi",
    		details:"persetujuan",
    		detailsBatch:"Persetujuan dalam jumlah besar di sini."
    	},
    	//一般
    	applyCode: 'Nomor ShenQingChan',
    	name:"Nama klien",
    	mobile:"Nomor ponsel",
    	idNumber:"Nomor KTP",
    	decisionResult:"Hasil persetujuan",
    	type:"Saluran",
        channel:"Detail subchannel",
	    product:"produk",
	    thenew:"Terbaru dengan detail",
	    subCase:"saluran",
	    subChannel:"Detail subchannel",
	 
	    //列表类
	    list:{
	    	   decisionState:"Persetujuan",
	    	 "applyCode": "Nomor ShenQingChan", 
			 "userName": "Nama klien", 
			 "userMobile":"Nomor ponsel",
			 "idNumber":"Nomor KTP",
			 "productId" : "produk",
		     "subCase" : "Saluran",
		     "subChannelDesc": "Detail subchannel",
			 "applicationAmount": "Jumlah untuk dipinjam",
			 "gmtCreate" : "Dalam hal waktu", 
			 "gmtDecision" : "Persetujuan waktu",
			 "userNameDecision" : "Persetujuan pengguna",
			 "decisionResult": "Hasil persetujuan",
			 "channelName" : "saluran", 
			 "queueNo": "Antrian saat ini", 
			 "chnName":"Para persetujuan",
			 "decisionResultDesc":"Catatan persetujuan",
			 newChnName:"Didorong sampai",
			 nowQueue:"Mendorong ke antrian",
			 nowResultName:"Keputusan terakhir setelah keputusan",
			 msg:"Informasi persetujuan",
	    },
	    //select
	    select:{
	    	
	    	v9:"Menunggu persetujuan level 1", 
	    	v10:"Menunggu persetujuan level 2", 
	    	v8:"Menunggu pembagian",
	    	v0:"Persetujuan melalui",
	    	v1:"Persetujuan menolak",
	    	vall:"semua",
	    	vq:"lain",
   	    	own:"Memberikan beberapa",
   	    	channel:"saluran",
   	    	other:"lain",
   	    	
	    },
	    //状态
	    states:{
	    	
	    	v9:"Menunggu persetujuan level 1",
	    	v10:"Menunggu persetujuan level 2",
	    	v8:"Menunggu pembagian",
	    	v0:"Persetujuan melalui",
	    	v1:"Persetujuan menolak",
	    	v11:"Level 1 ditolak",
   	    	v12:"Izin sekunder ditolak",
   	    	v10:"Disetujui level 1",
   	    	notcan:	"Tidak dapat persetujuan",
	    },
	    //提示
	    msg:{
	    	msg0:"Anda tidak memilih entri data apapun",
	    	msg1:'Hanya pilih hasil persetujuan sebagai data [menunggu distribusi]!',
	    	msg2:'Pilih saja antrian yang sama!',
	    	msg3:'Pilih saja saluran yang sama!',  //有的是单引号，有的是双引号
	    	msg10:"Silahkan pilih petugas pengiriman",
	    	msg11:"Operator sama",
	    	msg12:'Kau yakin ingin melakukan transfer? Status saat ini',	
	    	msg13:"Semua kasus",
	    	msg14:"Bagian disetel",
	    	msg21:"Anda tidak memilih data untuk disetujui!",
	    	msg22:'Anda yakin ingin persetujuan massal disetujui',
	    	msg23:'Catatan alkitab?',
	    },
	    //分配页面
	    assign:{
	    	td:"Petugas listrik nuklir",
	    	td2:"Persetujuan kedua",
	    	channel:"saluran"
	    },
	    //调案页面
	    allocation:{
	    	before:"Dipindahkan listrik nuklir",
	        after:"Alat nuklir",
	        select:"Silahkan pilih",
	    },
	    //隊列頁面
	    qe:{
	    	channel:"saluran",
	    	product:"produk",
	    	queue1 :"Kuantrian elektro-inti satu",
	    	queue2:"Baris persetujuan sekunder",
	    	username:"Akun kita",
	    	chnName:"Nama pengguna",
	    	allProduct:"Semua produk di bawah saluran",
	    	addQueue1:"Tambah baris pertama",
	    	addQueue2:"Tambah antrian sekunder",
	    	productCode:"Bertanggung jawab atas produk",
	    	queue:"Persetujuan antrian",
	 
	    },
	    //批量审批页面
	    batch:{
	    	waitAppr:"Tinggal persetujuan",
	    	batchAppr:"Menunggu persetujuan",
	    	
	    }
	    
	    
    },
}

