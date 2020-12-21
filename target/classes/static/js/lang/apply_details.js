/**
 * 审批详情国际化
 */

const zs_messages = {
    zh: {
    	a:{
    		msg:"借款申请信息",
    		his:"客户借款历史",
    		mobile:"通讯录/通话分析",
    		anss:"设备/运营商/地区分析",
    		report:"多头报告",
    		map:"关系图谱",
    		gps:"GPS分析",
    		call:'修改通话记录',
    		approval:'审批',
    	},
    	
        b:{	
        
	         contactName:"联系人姓名",
	         relationshipText:"联系人关系",
	         contactPhone:"联系人电话",
	       	 callResult:"通话情况",
	       	 other:" 借款情况",
          	 
        	 decisionResultDesc:"结果描述：",
         	 age:"年龄：",
        	 applyCode:"借款单号：",
        	 product:"产品名称：",
        	 gender:" 性别：",
        	 userName:"客户姓名：",
			 "userMobile":"电话", 
			 "idNumber":"证件号码：",
			 "productId" : "产品编号：",
		     "channelName" : "渠道：",
		     subChannel:" 子渠道编码:",
		     "subChannelDesc": " 渠道类别：",
			 "applicationAmount": "借款金额：",
			 "gmtCreate" : "进件时间：", 
			 term:"借款期限:",
			 workIndustry:"行业类型：",
			 isOpType:"职业类型",
			 userIncomeByCard:"月工资收入：",
			 companyName:" 单位名称：",
			 companyNumber:" 单位电话：",
			 companyAddrDetail:"单位地址：",
			 "assetHouseType" : " 住房情况：", 
			 addrDetail:" 居住地址：",
			 userEducation:"教育情况：",
			 userMarriage:" 婚姻状况：",
			 userChnNameDecision:"审批用户：",
			 gmtDecision:"审批时间：",
			 decisionResult:"审批结果：",
			 decisionReasonText:"审批备注：",
			 signCall:"通话情况	",
			 decision:"审批",
	    },
	    //状态
	    s:{
	    	
	    	v9:"等待一级审批",
	    	v10:"等待二级审批",
	    	v8:"等待分配",
	    	v0:"审批通过",
	    	v1:"审批拒绝",
	    	v11:"一级审批拒绝",
   	    	v12:"二级审批拒绝",
   	    	v10:"通过一级审批",
   	    	vnull:"未知",
   	    	vother:	"不可审批",
   	    	vall:"全部",
   	    	deger:"危险",
   	    	
   	    	loading:"查询中..",
	    },
	    	"未标记":"未标记",
	    	"未接通":"未接通",
	    	"已接通":"已接通",
	    	"未拨打":"未拨打",
	    o:{
    		msg:"$msg",
    		
    	},
    	h:{
    		applyId:"申请单号",
    		approveTime:"申请时间",
    		applyAmount:"借款金额",
    		totalNeedPaymentAmount:"应还总额",
    		applyPeriod:"借款期限",
    		deadlineDate:"应还日期",
    		clearDate:"结清日期",
    		applyState:"还款状态",
            currentStatus:"当前状态",
            overdueDays:"逾期天数",
            channelText:"进件渠道",
    	},
  	ca:{
  		name:"用户姓名",
		phone:"用户手机号",
    	recordSum:"运营商号码总数",
    	bookSum:"通讯录号码总数",
    	match:"运营商号码匹配通讯录个数",
    	rate:"运营商号码匹配通讯录切合度",
    		
    	},
    g:{
    	
    	baseMsg:"基本信息(单击节点查看)",
    	name:"姓名",
        idNumber:"身份证号",
        phone:"手机号",
        level:"级别",
        more:"查看详情",
        label:"标签",
        address:"地址",
        latitude:"维度",
        longitude:"经度",
    	
    }
    	
	    
    },
    en: {
    	o:{
    		msg:"￥信息",
    		
    	},
    
    	a:{
    		approval:'approval',
    		msg:"LoanApplicationInformation",
    		his:"CustomerBorrowingHistory",
    		mobile:"AddressBook/CallAnalysis",
    		anss:"Equipment/Operator/AreaAnalysis",
    		report:"LongReport",
    		map:"RelationalMap",
    		gps:"GPSAnalysis",
    		call:"call record"
    	},
    
        b:{
	       	 contactName:"name",
	         relationshipText:"relationship",
	         contactPhone:"telephone",
	       	 callResult:"calls situation",
	       	 other:" ",
        	
	       	 age:"age",
        	 applyCode:"LoanNumber",
        	 product:"ProductName",
        	 gender:"gender",
        	 userName:"CustomerName",
			 "userMobile":"phone", 
			 "idNumber":"ID card",
			 "productId" : "productCode",
		     "subCase" : "type",
		     subChannel:"channel",
		     "subChannelDesc": "Channel type",
			 "applicationAmount": "loan amount",
			 "gmtCreate" : "entryTime", 
			 term:"Loan period",
			 channelName:"channelName",
			 desc:"ApprovalRemarks",
			 workIndustry:"IndustryType",
			 isOpType:"OccupationalType",
			 userIncomeByCard:"MonthlyWageIncome",
			 companyName:"companyName",
			 companyNumber:"CompanyTelephone",
			 companyAddrDetail:"CompanyAddress",
			 "assetHouseType" : "HousingSituation", 
			 addrDetail:"ResidentialAddress",
			 userEducation:"EducationSituation",
			 userMarriage:"MaritalStatus",
			 userChnNameDecision:"ApprovalUser",
			 gmtDecision:"ApprovalTime",
			 decisionResult:"ApprovalResult",
			 decisionReasonText:"Remarks",
			 signCall:"Record calls	",
			 decision:"Approval",
	    },
       s:{
	    	
	    	v9:"wait 1",
	    	v10:"wait 2",
	    	v8:"wait allocation",
	    	v0:"success",
	    	v1:"fail",
	    	v11:"fail 1",
   	    	v12:"fail 2",
   	    	v10:"success 1",
   	    	notcan:	"approval is not allowed",
   	    	vnull:"-",
   	    	vother:	"other",
   	    	vall:"all",
   	    	deger:"deger",
   	    	loading:"loading",
	    },
	    "未标记":"unmark",
    	"未接通":"unconnected",
    	"已接通":"connected",
    	"未拨打":"No call",
    	h:{
    		applyId:"applyId",
    		approveTime:"applicationTime",
    		applyAmount:"loanAmount",
    		totalNeedPaymentAmount:"totalAmountPayable",
    		applyPeriod:"loanTerm",
    		deadlineDate:"dateToBeReturned",
    		clearDate:"settlementDate",
    		applyState:"repaymentStatus",
            currentStatus:"currentStatus",
            overdueDays:"theNumberOfDaysOverdue",
            channelText:"channel",
            
    	},
      	ca:{
        	recordSum:'total  of calls recorded',
        	bookSum:"Total number of address book Numbers",
        	match:"Number of matches",
            rate:"Matching proportion",
    		phone:"phone number",
    		name:"name",
        		
        	},
            g:{
            	
            	baseMsg:"Basic information",
            	name:"name",
                idNumber:"ID number",
                phone:"phone number",
                level:"level",
                more:"View details",
                label:"label",
                address:"address",
                latitude:"latitude",
                longitude:"longitude",
            	
            }
	    
    },
    id: {
    	a:{
    		msg:"Informasi aplikasi pinjaman",
    		his:"Riwayat pinjaman pelanggan",
    		mobile:"Analisa kontak/telepon",
    		anss:"Peralatan/operator/analisis daerah",
    		report:"Laporan ekor",
    		map:"Profil hubungan",
    		gps:"Analisis g. P.",
    		call:'Memodifikasi catatan panggilan',
    		approval:'persetujuan',
    	},
    	
        b:{	
        
	         contactName:"Nama kontak:",
	         relationshipText:"Hubungan kontak:",
	         contactPhone:"Nomor kontak:",
	       	 callResult:"Status panggilan:",
	       	 other:" Peminjaman  :",
          	 
        	 decisionResultDesc:"Deskripsi hasilnya:",
         	 age:"Umur:",
        	 applyCode:"Nomor debit lembar:",
        	 product:"Nama produk:",
        	 gender:" Gender:",
        	 userName:"Nama klien:",
			 "userMobile":"telepon", 
			 "idNumber":"Nomor identitas:",
			 "productId" : "Nomor produk:",
		     "channelName" : "Channel:",
		     subChannel:" Kode sub-saluran:",
		     "subChannelDesc": " Kategori saluran:",
			 "applicationAmount": "Jumlah pinjaman:",
			 "gmtCreate" : "Waktu masuk:", 
			 term:"Waktu pinjaman:",
			 workIndustry:"Tipe industri:",
			 isOpType:"Tipe profesional",
			 userIncomeByCard:"Gaji bulanan:",
			 companyName:" Nama unit:",
			 companyNumber:" Unit panggilan:",
			 companyAddrDetail:"Alamat unit:",
			 "assetHouseType" : " Status perumahan:", 
			 addrDetail:" Alamat rumah:",
			 userEducation:"Situasi di education:",
			 userMarriage:" Status pernikahan:",
			 userChnNameDecision:"Pengguna persetujuan:",
			 gmtDecision:"Waktu persetujuan:",
			 decisionResult:"Hasil persetujuan:",
			 decisionReasonText:"Catatan persetujuan:",
			 signCall:"Situasi",
			 decision:"persetujuan",
	    },
	    //状态
	    s:{
	    	
	    	v9:"Menunggu persetujuan level 1.",
	    	v10:"Menunggu persetujuan level 2",
	    	v8:"Menunggu pembagian",
	    	v0:"Persetujuan melalui",
	    	v1:"Persetujuan menolak",
	    	v11:"Level 1 ditolak.",
   	    	v12:"Izin sekunder ditolak.",
   	    	v10:"Disetujui level 1.",
   	    	vnull:"Tak dikenal.",
   	    	vother:	"Tidak dapat persetujuan",
   	    	vall:"semua",
   	    	deger:"berbahaya",
   	    	
   	    	loading:"Dalam pencarian",
	    },
	    	"未标记":"Tidak ditandai",
	    	"未接通":"Sedang tidak aktif",
	    	"已接通":"Telah dihubungi",
	    	"未拨打":"Belum menelepon",
	    o:{
    		msg:"$msg",
    		
    	},
    	h:{
    		applyId :" nomor aplikasi ",

    		approveTime :" waktu aplikasi",

    		applyAmount :" jumlah pinjaman ",

    		totalNeedPaymentAmount :" totalnya harus dikembalikan ",

    		applyPeriod :" tempoh pinjaman ",

    		deadlineDate :" tanggal pembalasan ",

    		clearDate:" tanggal penutupan ",

    		applyState :" negara pembayaran ",

    		currentStatus:" status saat ini ",

    		overdueDays :" hari yang terlambat",

    		channelText :" channel feed "
    	},
      ca:{
      		name:"nama",
    		phone:"Tempat tidur.",
        	recordSum:"Jumlah catatan panggilan",
        	bookSum:"Jumlah total nomor telepon",
        	match:"The number of fit ",
        	rate:"persentase",
    		
        	},
            g:{
            	
            	baseMsg:"Pesan mendasar",
            	name:"nama",
                idNumber:"Nomor KTP",
                phone:"Tempat tidur.",
                level:"tingkat",
                more:"rinciannya",
                label:"label",
                address:"alamat",
                latitude:"dimensi",
                longitude:"Garis bujur.",
            	
            }
    },
}

