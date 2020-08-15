
<template>
    <div class="box">
        <div class="top-box">
            <van-dropdown-menu>
                <van-dropdown-item
                    v-model="params.departmentorganId"
                    :options="subjects"
                    @change="subjectsChange"
                />
                <van-dropdown-item v-model="params.theTitle" :options="theTitleList" @change="theTitleChange"/>
            </van-dropdown-menu>
            <van-divider class="z-custom">{{this.params.type ? '今日' : '七日内'}}可预约</van-divider>
        </div>
        <ul>
            <li v-for="(item, index) in list" :key="index" class="flex-box align-center justify-s-b common-block item">
                <img class="item-img" :src="item.photoUrl || require('@/assets/image/d-male.png')" alt="">
                <div class="flex-1">
                    <div class="item-text-1">{{item.name}}</div>
                    <div class="item-text-2">{{item.job}}</div>
                </div>
                <van-button plain hairline round size="small" class="item-btn z-btn" @click="link(item)">挂号</van-button>
            </li>
        </ul>
        <van-empty v-show="loadCount === 1 && !list.length" image="search" description="没有查到相关医生" />
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator';
    import {DropdownMenu, DropdownItem, Divider, Empty, Button} from 'vant';

    Vue.use(DropdownMenu);
    Vue.use(DropdownItem);
    Vue.use(Divider);
    Vue.use(Empty);
    Vue.use(Button);


    @Component
    export default class DepartmentDoctorList extends Vue {
        params: IOBJ = {
            departmentorganId: '105',
            theTitle: undefined,
            type: '2'
        };

        loadCount: 0 | 1 = 0;
        subjects: IOBJ[] = [];
        theTitleList: IOBJ[] = [];

        oldList: IOBJ[] = [];
        list: IOBJ[] = [];

        link (item: IOBJ) {
            const type = this.params.type;
            const {photoUrl, name, job, departmentorganId} = item;
            this.$router.push({name: 'registrationDoctor', query: {photoUrl, name, job, type}, params: {departmentorganId}});
        }

        subjectsChange (e: string) {
            // console.log(e);
            this.params.departmentorganId = e;
            this.getList();
        }

        theTitleChange (e: string | undefined) {
            this.list = e === undefined ? [...this.oldList] : this.oldList.filter((item) => item.job === e);
        }

        // 获取医生职位
        handleTheTitle (list: IOBJ[]) {
            const set = new Set();
            // console.log(list);
            list.forEach((item) => {
                !item.job && console.log('少了job', item);
                !!item.job && set.add(item.job);
            });

            this.params.theTitle = undefined;
            // debugger;
            this.theTitleList = [{text: '医生职称', value: undefined}].concat((Array.from(set)).map((value: any | string) => ({text: value, value})));
        }

        // 处理list数据
        handleList (list: IOBJ[]) {
            const type = this.params.type;
            this.oldList = list.filter((item) => type === '0' ? item.isReg === '1' : item.sevenDaySource === '1');
            this.list = [...this.oldList];
        }

        // 获取顶部数据 isOne是否有默认科室id
        getTopData (isOne: boolean) {
            // querydepartmentlist
            const res = {
                department: [
                    {
                        'organId': '105',
                        'name': '妇科',
                        'numDoctors': '',
                        'ddesc': '妇科位于我院2号楼、3号楼五楼全层。是集医疗、科研、教学为一体的重点科室，是我院优质护理服务示范科室。具有一支理论基础扎实、临床经验丰富的专业技术队伍，现有医务人员42人，其中高中级以上职称12人，开放床位54张。年诊疗6万余人次，年出院2500余人次。是广东省妇幼保健院宫颈疾病防治中心廉江分中心、广州市妇女儿童医院的技术指导点。n科室拥有国内外先进的检查、治疗及手术设备：宫腔镜、腹腔镜、电子阴道镜、彩色阴道B超、Leep刀、钨钼双靶乳腺机、监护仪等。n开展业务：妇科炎症、妊娠剧吐、先兆流产、不孕不育、功血、子宫肌瘤、子宫腺肌瘤、卵巢肿瘤、多囊卵巢综合症、卵巢巧克力囊肿、宫外孕、子宫脱垂、阴道前后壁膨出、宫颈病变以及卵巢恶性肿瘤、子宫内膜癌、宫颈癌等各种疾病的治疗。并开展高新技术筛查宫颈癌以及无痛流产、无痛放取环技术、宫腔粘连的诊断及治疗等业务。n乳腺专科门诊现拥有意大利吉特最新型号的钨钼双靶乳腺机。该机能够清晰显示乳腺各个层次的微细结构，特别是微细钙化。提高了早期乳腺癌的发现率及各种乳腺疾病的诊断精确度，为乳腺癌的早发现、早干预、早治疗创造了条件。n科室特色n科室以微创技术诊治妇科疾病为特色。开展腹腔镜治疗子宫肌瘤、子宫腺肌瘤、子宫内膜异位症和卵巢肌瘤肿瘤、宫外孕等手术。宫腔镜治疗粘膜下肌瘤、功血、宫颈息肉以及宫内环嵌顿等各种宫腔疾病。开展了经阴道手术治疗子宫脱垂、阴道前后壁膨出、压力性尿失禁等。在妇科肿瘤方面开展宫颈癌广泛子宫切除术、盆腔淋巴清扫术等。n服务电话：0759--6600710n',
                        'departAddr': ''
                    },
                    {
                        'organId': '106',
                        'name': '产科',
                        'numDoctors': '',
                        'ddesc': "我院产科创建于六十年代，经过50多年的沧桑砥砺，已发展成为粤西地区同类妇幼保健院最具发展代表性的科室之一，科室布局合理，环境舒适。是全市产科技术指导及业务培训中心。产科现有医护人员82人，其中高中级以上职称36人，专业人才队伍配置合理，为广大孕产妇健康提供必要的技术支撑。设产前、产后2个区，共100张床位。n服务范围：n1.开展顺产、难产接生：儿头吸引产、臂臀助产、产钳助产等难产接生及时识别异常分娩，减少分娩期并发症的发生。n2.病理产科治疗：开展先兆早产、早产、胎儿宫内窘迫监测、宫内发育迟缓、妊娠高血压疾病的防治及妊娠合并糖尿病的筛查和治疗、妊娠合并胆汁淤积综合征。n3.开展急症和择期剖宫产手术：救治胎盘早剥、DIC、产后出血、羊水栓塞等急危重症产妇经验丰富。新式剖宫产术后产妇24小时后可下床活动，切口小，愈合快，腹部横切口愈合美观。n4.推行产儿科合作制度，新生儿科医生进产房、参加产前会诊，使新生儿在产前、产时、产后至出院都能得到新生儿专科医生定时诊查，以确保新生儿的安全。n5.进行母乳喂养指导和知识宣教，运用手法推拿、仪器治疗等帮助产妇早开奶。n6.提供孕妇产前、产后保健服务：中期结构筛查、唐氏筛查、筛查高危妊娠、筛查21－三体综合征等先天性疾病，提供孕期至产后唐氏筛查：妊娠期高血压疾病筛查妊娠期糖尿病筛查（OGTT）、中期结构筛查等'一条龙'服务，进行全面的健康检查和怀孕早、中、晚期的保健咨询、指导，进行孕期营养分析，减少巨大儿出生。n特色服务n1.陪伴待产：孕妇可在一亲属陪伴下进入待产室，在亲情呵护下缓解分娩前的紧张情绪，为准妈妈提供心理支持与安慰。n2.小组负责制陪伴分娩：从进入产程到产后2小时，由一组专业助产士陪护，负责其全产程的观察和接生，给予产妇生理、心理、情感上的支持及产后母乳喂养指导等健康知识普及教育、减轻分娩疼痛，提高顺产率。n3.无痛分娩：（1）2009年12月我院在全市范围内率先开展硬膜外麻醉无痛分娩技术。该技术可以显著减轻产妇分娩时的疼痛，有效缩短产程，降低剖宫产率，提高自然分娩率。（2）引进妇产科镇痛电子治疗仪又称分娩镇痛仪，镇痛仪是利用生物电刺激原理，为非药物分娩镇痛，对母婴均无不良反应，安全可靠，产妇有了更多选择。n4.助产士门诊：指导准妈妈腹式呼吸及拉玛泽呼吸方法，介绍多种方法促进自然分娩。n5.孕妇学校：定期举办培训班，专业医师讲解孕产期保健知识及新生儿常识，以及传授相关健康教育知识。n6.产后盆底康复：盆底康复治疗仪无创无痛，在专业诊疗医师指导下，帮助患者修复盆底肌肉，改善盆腔器官脱垂、张力性尿失禁、阴道前后壁松弛等症状。n咨询电话：0759-6626906n",
                        'departAddr': ''
                    },
                    {
                        'organId': '117',
                        'name': '新生儿科',
                        'numDoctors': '',
                        'ddesc': '新生儿科于1999年3月在粤西地区同级医疗机构中率先设立了无家属陪护的新生儿室。目前该科拥有医护人员39人，其中，高中级以上职称5人。承担着全市新生儿疾病的诊治、护理及危重新生儿的转运和抢救等任务。n该科布局合理、环境舒适，设有重症监护区、早产儿区、足月儿区、感染隔离区等，病区采用独立新风净化系统及空气消毒机双重消毒，目前共开放床位60张，拥有英国SLE5000高频+常频呼吸机、美国纽邦E-360呼吸机、无创CPAP机、床边X光机、新生儿转运暖箱、多参数监护仪、新生儿辐射抢救台、早产儿培养箱、双面黄疸治疗箱等一大批先进的医疗设备，是粤西地区设备及条件较先进的新生儿科。n在全市范围内率先应用肺表面活性物质联合呼吸机治疗早产儿肺透明膜病、胎粪吸入综合征、急性肺损伤等重大肺部疾病，在早产儿、低出生体重儿、新生儿黄疸、新生儿缺氧缺血性脑病、新生儿窒息复苏、新生儿呼吸衰竭、新生儿溶血病、气胸等疾病治疗方面处于领先水平。早在1999年便成功抢救一例出生体重900克的超低出生体重儿。2013年成功抢救胎龄27周、出生体重分别是850克、880克、900克的三胞胎超低出生体重儿。近年来，还联合小儿外科开展新生儿手术，包括新生儿胃肠穿孔、膈疝、先天性巨结肠、先天性肠闭锁、肛门闭锁、幽门梗阻、脐膨出等手术，取得良好的效果。n另外，我院制定了完善的产儿科合作制度，如新生儿科医生进产房、参加产前会诊，使新生儿在产前、产时、产后直至出院都能得到新生儿专科医生定时检查，以确保新生儿的安全。n服务特色：n1.由经验丰富的儿科医生出车接诊，现场抢救、转运更专业、更安全。n2.配备新生儿转运暖箱、车载呼吸机等，转运条件更成熟，转运网络覆盖全市，辐射至广西、茂名等地区。n3.科室抢救团队技术精湛。技术骨干全部经过国家级或省级大医院专业性的进修培训。医疗目标由降低新生儿病死率转到在降低病死率的基础上最大限度地减少致残率、提高患儿的生存和生活质量。n4.新生儿特色技术：呼吸机的应用、新生儿外科手术后监护护理等。n科室服务电话：0759-6600697n',
                        'departAddr': ''
                    },
                    {
                        'organId': '124',
                        'name': '儿童保健科',
                        'numDoctors': '',
                        'ddesc': "儿童保健科是以体检、咨询、评价、指导、干预一体化,防治结合,身心并重的儿童保健服务模式,为不同层次的儿童提供不同需求的保健服务,被人们誉为儿童健康的'守护神'。n作为全市儿童保健技术指导中心,儿童保健科承担着全市的儿童健康管理、技术指导、培训、督导工作；同时在院内开设了各种具有特色的儿童保健门诊。n1、生长发育监测目的是研究小儿生长发育的规律及其影响因素,掌握体格发育状况,及时发现有无营养不良或营养过剩等发育异常情况。n2、儿童早期教育从出生后就可以进行早期教育,早期教育就是利用婴幼儿大脑的未成熟,极具可塑性的特点,进行教育、训练和培养。n3、新生儿行为测定是专门为出生后两天至一个月内足月健康儿检查的一种方法,目的通过测查即可发现各种有害因素造成的轻微脑损伤。n4、听力筛查是指孩子出生后24小时后通过耳声发射筛查仪对新生儿的听觉系统进行综合评估,以确定孩子的听力系统发育是否正常的一种筛查方法。n5、智力测评是为儿童在出生后第3个月、5个月、8个月、1岁各做一次智力检查,1岁到3岁每半年一次,3岁以后一年一次。直到16岁,定期给儿童进行智力和心理检查,除了能了解现阶段的发育情况外,对发现的问题还能给予及时的指导和训练。n6、婴儿水疗、中药熏蒸、抚触　游泳是在专门的安全措施的保护下,由经过专业培训的人员对婴儿实施的水中游泳活动,游泳可以促进身高体重的增长和新生儿黄疸的消退,提高婴儿感觉细胞的敏感性和身体的协调性；中药熏蒸集中了中医药疗、热疗、汽疗、中药离子渗透治疗疗法等多种功能,融热度、湿度、药物浓度于一体,因病施治,药物对症,达到治病、防病、保健的目的。抚触是一种历史悠久的医疗技术,具有完善的科学理论指导和确定的临床效果的支持,通过婴儿皮肤感官温和的刺激,有益于婴儿健康的发育,可以促进婴儿体重的增长及应激能力的提高,可以改善消化系统和增强睡眠,可以改善血液循环,增强免疫力。n7、特殊视力检查采用美国进口视力筛选仪,检查小儿眼睛的屈光度,能快速检测近视、远视、散光、弱视等各种潜在的视力问题,并且有安全的、无创的特点,适用于婴幼儿和学龄前儿童的视力筛查。n8、超声骨密检测根据超声波在人体骨骼中的穿透速度及超声衰减程度来衡量机体中骨质含量,临床上应用于检测患者有无骨质疏松,了解骨骼密度变化情况,诊断骨量减少,骨质疏松症。而且因其有无辐射性、无入侵性的优点,更加适合孕妇,哺乳期女性及绝经后妇女、老年人和儿童因饮食不均衡而导致缺钙等骨质检测。n9、儿童口腔保健儿童牙齿与品腔的正常发育是健康的基础,口腔的健康直接影响和促进儿童生长发育,为了孩子拥有一副健康而坚固的牙齿,口腔保健应由婴幼儿开始,根据婴幼儿的生长发育过程指导口腔保健,检查乳牙萌出、乳牙护理、换牙、龋齿防治,及早发现处理鹅口疮等口腔疾病。n10、儿童眼保健：针对3.5－4岁儿童进行视力、斜视、弱视及屈光不正的筛查,并进行矫治。n11、儿童集体体检：每年对全市托幼机构开展一次儿童集体体检。通过对幼儿园儿童健康状况的评估、分析,为托幼机构、幼儿园的卫生保健管理及全市儿童健康规划管理提供科学的依据和合理建议。n服务电话：0759--6601150　　n",
                        'departAddr': ''
                    },
                    {
                        'organId': '140',
                        'name': '放射科',
                        'numDoctors': '',
                        'ddesc': '',
                        'departAddr': ''
                    },
                    {
                        'organId': '822',
                        'name': '儿科一区门诊',
                        'numDoctors': '',
                        'ddesc': '',
                        'departAddr': ''
                    },
                    {
                        'organId': '1002',
                        'name': '儿科',
                        'numDoctors': '',
                        'ddesc': '小儿外科位于我院3号楼七楼,是我院重点发展的科室之一，也是我市乃至周边市、县为数不多的小儿专科科室，主要开展业务有小儿泌尿外科、小儿普外科、小儿骨外科及新生儿外科。为适应小儿外科的持续发展及提高手术的成功率、疾病确诊率，我院引进了层流手术室、先进的小儿麻醉机、小儿呼吸机、奥林巴斯小儿腹腔镜、奥林巴斯超声刀、三维彩超、数控小儿空气灌肠机等先进设备，专业的疾病诊断和治疗水平得到了大大的提高。小儿外科成立至今已成功开展小儿外科手术近万千台，在粤西地区同级妇幼保健院中有较大影响力，优势有包括小儿腹腔镜手术、多种小儿外科疾病及手术术式、小儿阴茎整形（隐匿性阴茎矫形术、先天性尿道下裂手术、阴茎弯曲矫正术等）、先天性肛门闭锁（低位）肛门1期成形术、先天性肛门闭锁（高位）1期造瘘术、Pena术及造瘘回纳术、先天性巨结肠经肛门Soave巨结肠根治术、先天性膈疝修补术、新生儿肠闭锁、先天性幽门肥厚、坏死肠等，均取得成功，随诊生活质量良好。n医护人员在广州市妇女儿童医疗中心、中山大学孙逸仙纪念医院、佛山市第一人民医院、广东医科大学附属医院等上级医院小儿外科专业进修学习，并有良好的合作关系，作为广州市妇女儿童医疗中心、中山大学孙逸仙纪念医院的技术指导点，小儿外科所采用的手术术式是目前较先进的手术术式，特别是小儿腹腔镜手术具有手术安全、切口小、创伤小、出血少、恢复快、术后美观等优点，并结合小儿多动、不配合的特点，采用静脉全麻及切口皮内缝合（免拆线）等技术，深受广大患儿家长的好评。n',
                        'departAddr': ''
                    },
                    {
                        'organId': '1342',
                        'name': '儿科急诊',
                        'numDoctors': '',
                        'ddesc': '',
                        'departAddr': ''
                    }
                ]
            };

            const subjects: IOBJ[] = res.department;

            subjects.forEach((item) => {
                item.text = item.name;
                item.value = item.organId;
            });

            this.subjects = subjects;
            if (!isOne) {
                this.params.departmentorganId = subjects[0].value;
                this.getList();
            }
        }

        getList () {
            // queryDoctorlistSource
            // const departmentorganId = this.params.departmentorganId;
            this.loadCount = 0;
            const params = this.params;
            const res = {
                Doctor: [
                    {
                        'account': '207',
                        'sevenDaySource': '0',
                        'name': '牟伟',
                        'departmentorganId': '105',
                        'ddesc': null,
                        'specialty': null,
                        'job': '医师',
                        'specialtyName': null,
                        'treatdiseases': null,
                        'achievement': null,
                        'socialResponsibility': null,
                        'papersPublished': null,
                        'peopleHomepage': null,
                        'organdoctorId': '154',
                        'photoUrl': 'http://weixin.ljfy.com.cn:8099/pic/牟伟.jpg',
                        'isReg': '0'
                    },
                    {
                        'account': '310',
                        'sevenDaySource': '0',
                        'name': '李贞',
                        'departmentorganId': '105',
                        'ddesc': null,
                        'specialty': null,
                        'job': '主治医师',
                        'specialtyName': null,
                        'treatdiseases': null,
                        'achievement': null,
                        'socialResponsibility': null,
                        'papersPublished': null,
                        'peopleHomepage': null,
                        'organdoctorId': '156',
                        'photoUrl': 'http://weixin.ljfy.com.cn:8099/pic/李贞.jpg',
                        'isReg': '0'
                    },
                    {
                        'account': '251',
                        'sevenDaySource': '0',
                        'name': '张文丽',
                        'departmentorganId': '105',
                        'ddesc': null,
                        'specialty': null,
                        'job': '副主任医师',
                        'specialtyName': null,
                        'treatdiseases': null,
                        'achievement': null,
                        'socialResponsibility': null,
                        'papersPublished': null,
                        'peopleHomepage': null,
                        'organdoctorId': '198',
                        'photoUrl': 'http://weixin.ljfy.com.cn:8099/pic/张文丽.jpg',
                        'isReg': '0'
                    },
                    {
                        'account': '046',
                        'sevenDaySource': '1',
                        'name': '麦敏',
                        'departmentorganId': '105',
                        'ddesc': null,
                        'specialty': null,
                        'job': '副主任医师',
                        'specialtyName': null,
                        'treatdiseases': null,
                        'achievement': null,
                        'socialResponsibility': null,
                        'papersPublished': null,
                        'peopleHomepage': null,
                        'organdoctorId': '260',
                        'photoUrl': 'http://weixin.ljfy.com.cn:8099/pic/麦敏.jpg',
                        'isReg': '0'
                    },
                    {
                        'account': '020',
                        'sevenDaySource': '0',
                        'name': '陈少珍',
                        'departmentorganId': '105',
                        'ddesc': null,
                        'specialty': null,
                        'job': '主治医师',
                        'specialtyName': null,
                        'treatdiseases': null,
                        'achievement': null,
                        'socialResponsibility': null,
                        'papersPublished': null,
                        'peopleHomepage': null,
                        'organdoctorId': '281',
                        'photoUrl': 'http://weixin.ljfy.com.cn:8099/pic/陈少珍.jpg',
                        'isReg': '0'
                    },
                    {
                        'account': '177',
                        'sevenDaySource': '1',
                        'name': '黎春',
                        'departmentorganId': '105',
                        'ddesc': null,
                        'specialty': null,
                        'job': '主治医师',
                        'specialtyName': null,
                        'treatdiseases': null,
                        'achievement': null,
                        'socialResponsibility': null,
                        'papersPublished': null,
                        'peopleHomepage': null,
                        'organdoctorId': '283',
                        'photoUrl': 'http://weixin.ljfy.com.cn:8099/pic/黎春.jpg',
                        'isReg': '0'
                    },
                    {
                        'account': '058',
                        'sevenDaySource': '1',
                        'name': '陈翠玲',
                        'departmentorganId': '105',
                        'ddesc': null,
                        'specialty': null,
                        'job': '主治医师',
                        'specialtyName': null,
                        'treatdiseases': null,
                        'achievement': null,
                        'socialResponsibility': null,
                        'papersPublished': null,
                        'peopleHomepage': null,
                        'organdoctorId': '286',
                        'photoUrl': 'http://weixin.ljfy.com.cn:8099/pic/陈翠玲.jpg',
                        'isReg': '0'
                    },
                    {
                        'account': '181',
                        'sevenDaySource': '1',
                        'name': '黄琴',
                        'departmentorganId': '105',
                        'ddesc': null,
                        'specialty': null,
                        'job': '主治医师',
                        'specialtyName': null,
                        'treatdiseases': null,
                        'achievement': null,
                        'socialResponsibility': null,
                        'papersPublished': null,
                        'peopleHomepage': null,
                        'organdoctorId': '287',
                        'photoUrl': 'http://weixin.ljfy.com.cn:8099/pic/黄琴.jpg',
                        'isReg': '0'
                    },
                    {
                        'account': '160',
                        'sevenDaySource': '1',
                        'name': '邹霞',
                        'departmentorganId': '105',
                        'ddesc': null,
                        'specialty': null,
                        'job': '副主任医师',
                        'specialtyName': null,
                        'treatdiseases': null,
                        'achievement': null,
                        'socialResponsibility': null,
                        'papersPublished': null,
                        'peopleHomepage': null,
                        'organdoctorId': '291',
                        'photoUrl': 'http://weixin.ljfy.com.cn:8099/pic/邹霞.jpg',
                        'isReg': '0'
                    },
                    {
                        'account': '087',
                        'sevenDaySource': '1',
                        'name': '郑远华',
                        'departmentorganId': '105',
                        'ddesc': null,
                        'specialty': null,
                        'job': '副主任医师',
                        'specialtyName': null,
                        'treatdiseases': null,
                        'achievement': null,
                        'socialResponsibility': null,
                        'papersPublished': null,
                        'peopleHomepage': null,
                        'organdoctorId': '294',
                        'photoUrl': 'http://weixin.ljfy.com.cn:8099/pic/郑远华.jpg',
                        'isReg': '0'
                    },
                    {
                        'account': '112',
                        'sevenDaySource': '0',
                        'name': '刘婷',
                        'departmentorganId': '105',
                        'ddesc': null,
                        'specialty': null,
                        'job': '主治医师',
                        'specialtyName': null,
                        'treatdiseases': null,
                        'achievement': null,
                        'socialResponsibility': null,
                        'papersPublished': null,
                        'peopleHomepage': null,
                        'organdoctorId': '323',
                        'photoUrl': 'http://weixin.ljfy.com.cn:8099/pic/刘婷.jpg',
                        'isReg': '0'
                    },
                    {
                        'account': '042',
                        'sevenDaySource': '0',
                        'name': '黄梅燕',
                        'departmentorganId': '105',
                        'ddesc': null,
                        'specialty': null,
                        'job': '主治医师',
                        'specialtyName': null,
                        'treatdiseases': null,
                        'achievement': null,
                        'socialResponsibility': null,
                        'papersPublished': null,
                        'peopleHomepage': null,
                        'organdoctorId': '349',
                        'photoUrl': 'http://weixin.ljfy.com.cn:8099/pic/黄梅燕.jpg',
                        'isReg': '0'
                    },
                    {
                        'account': '037',
                        'sevenDaySource': '1',
                        'name': '郑秀金',
                        'departmentorganId': '105',
                        'ddesc': null,
                        'specialty': null,
                        'job': '副主任医师',
                        'specialtyName': null,
                        'treatdiseases': null,
                        'achievement': null,
                        'socialResponsibility': null,
                        'papersPublished': null,
                        'peopleHomepage': null,
                        'organdoctorId': '378',
                        'photoUrl': 'http://weixin.ljfy.com.cn:8099/pic/郑秀金.jpg',
                        'isReg': '0'
                    },
                    {
                        'account': '038',
                        'sevenDaySource': '0',
                        'name': '孙光平',
                        'departmentorganId': '105',
                        'ddesc': null,
                        'specialty': null,
                        'job': '主治医师',
                        'specialtyName': null,
                        'treatdiseases': null,
                        'achievement': null,
                        'socialResponsibility': null,
                        'papersPublished': null,
                        'peopleHomepage': null,
                        'organdoctorId': '385',
                        'photoUrl': 'http://weixin.ljfy.com.cn:8099/pic/孙光平.jpg',
                        'isReg': '0'
                    },
                    {
                        'account': '074',
                        'sevenDaySource': '0',
                        'name': '罗璇',
                        'departmentorganId': '105',
                        'ddesc': null,
                        'specialty': null,
                        'job': '主治医师',
                        'specialtyName': null,
                        'treatdiseases': null,
                        'achievement': null,
                        'socialResponsibility': null,
                        'papersPublished': null,
                        'peopleHomepage': null,
                        'organdoctorId': '390',
                        'photoUrl': 'http://weixin.ljfy.com.cn:8099/pic/罗璇.jpg',
                        'isReg': '0'
                    },
                    {
                        'account': '005',
                        'sevenDaySource': '0',
                        'name': '廖梅娟',
                        'departmentorganId': '105',
                        'ddesc': null,
                        'specialty': null,
                        'job': '主治医师',
                        'specialtyName': null,
                        'treatdiseases': null,
                        'achievement': null,
                        'socialResponsibility': null,
                        'papersPublished': null,
                        'peopleHomepage': null,
                        'organdoctorId': '704',
                        'photoUrl': 'http://weixin.ljfy.com.cn:8099/pic/廖梅娟.jpg',
                        'isReg': '0'
                    },
                    {
                        'account': '340',
                        'sevenDaySource': '0',
                        'name': '覃肖凤',
                        'departmentorganId': '105',
                        'ddesc': null,
                        'specialty': null,
                        'job': '医师',
                        'specialtyName': null,
                        'treatdiseases': null,
                        'achievement': null,
                        'socialResponsibility': null,
                        'papersPublished': null,
                        'peopleHomepage': null,
                        'organdoctorId': '1803',
                        'photoUrl': 'http://weixin.ljfy.com.cn:8099/pic/覃肖凤.jpg',
                        'isReg': '0'
                    },
                    {
                        'account': '381',
                        'sevenDaySource': '0',
                        'name': '徐丽斯',
                        'departmentorganId': '105',
                        'ddesc': null,
                        'specialty': null,
                        'job': '主治医师',
                        'specialtyName': null,
                        'treatdiseases': null,
                        'achievement': null,
                        'socialResponsibility': null,
                        'papersPublished': null,
                        'peopleHomepage': null,
                        'organdoctorId': '2881',
                        'photoUrl': 'http://weixin.ljfy.com.cn:8099/pic/徐丽斯.jpg',
                        'isReg': '0'
                    },
                    {
                        'account': '407',
                        'sevenDaySource': '0',
                        'name': '黄土凤',
                        'departmentorganId': '105',
                        'ddesc': null,
                        'specialty': null,
                        'job': '医师',
                        'specialtyName': null,
                        'treatdiseases': null,
                        'achievement': null,
                        'socialResponsibility': null,
                        'papersPublished': null,
                        'peopleHomepage': null,
                        'organdoctorId': '4344',
                        'photoUrl': 'http://weixin.ljfy.com.cn:8099/pic/黄土凤.jpg',
                        'isReg': '0'
                    },
                    {
                        'account': '553',
                        'sevenDaySource': '1',
                        'name': '张敏',
                        'departmentorganId': '105',
                        'ddesc': null,
                        'specialty': null,
                        'job': '副主任医师',
                        'specialtyName': null,
                        'treatdiseases': null,
                        'achievement': null,
                        'socialResponsibility': null,
                        'papersPublished': null,
                        'peopleHomepage': null,
                        'organdoctorId': '4724',
                        'photoUrl': 'http://weixin.ljfy.com.cn:8099/pic/张敏.jpg',
                        'isReg': '0'
                    },
                    {
                        'account': '530',
                        'sevenDaySource': '0',
                        'name': '陈文思',
                        'departmentorganId': '105',
                        'ddesc': null,
                        'specialty': null,
                        'job': '医师',
                        'specialtyName': null,
                        'treatdiseases': null,
                        'achievement': null,
                        'socialResponsibility': null,
                        'papersPublished': null,
                        'peopleHomepage': null,
                        'organdoctorId': '4923',
                        'photoUrl': 'http://weixin.ljfy.com.cn:8099/pic/陈文思.jpg',
                        'isReg': '0'
                    },
                    {
                        'account': '569',
                        'sevenDaySource': '0',
                        'name': '张如霞',
                        'departmentorganId': '105',
                        'ddesc': null,
                        'specialty': null,
                        'job': '医师',
                        'specialtyName': null,
                        'treatdiseases': null,
                        'achievement': null,
                        'socialResponsibility': null,
                        'papersPublished': null,
                        'peopleHomepage': null,
                        'organdoctorId': '5059',
                        'photoUrl': 'http://weixin.ljfy.com.cn:8099/pic/张如霞.jpg',
                        'isReg': '0'
                    },
                    {
                        'account': '024',
                        'sevenDaySource': '0',
                        'name': '陈妃福',
                        'departmentorganId': '105',
                        'ddesc': null,
                        'specialty': null,
                        'job': '',
                        'specialtyName': null,
                        'treatdiseases': null,
                        'achievement': null,
                        'socialResponsibility': null,
                        'papersPublished': null,
                        'peopleHomepage': null,
                        'organdoctorId': '5786',
                        'photoUrl': 'http://weixin.ljfy.com.cn:8099/pic/陈妃福.jpg',
                        'isReg': '0'
                    },
                    {
                        'account': '036',
                        'sevenDaySource': '0',
                        'name': '梁思颖',
                        'departmentorganId': '105',
                        'ddesc': null,
                        'specialty': null,
                        'job': '见习医师',
                        'specialtyName': null,
                        'treatdiseases': null,
                        'achievement': null,
                        'socialResponsibility': null,
                        'papersPublished': null,
                        'peopleHomepage': null,
                        'organdoctorId': '6023',
                        'photoUrl': 'http://weixin.ljfy.com.cn:8099/pic/梁思颖.jpg',
                        'isReg': '0'
                    }
                ]
            };

            this.handleTheTitle(res.Doctor);
            this.handleList(res.Doctor);
            this.loadCount = 1;
        }

        created () {
            // subjectsId 科室id
            // type 0今日（isReg === '1'） 2预约（sevenDaySource === '1'）
            const {subjectsId, type} = this.$route.query;
            this.params.departmentorganId = (subjectsId || '') + '';
            this.params.type = (type || '') + '';

            const departmentorganId = this.params.departmentorganId;
            this.getTopData(!!departmentorganId);
            !!departmentorganId && this.getList();

            console.log(this.loadCount);
        }
    }
</script>

<style lang="scss" scoped>
    .box {
        min-height: 100%;
        background: #fff;
        // overflow: hidden;
    }
    .top-box{
        position: sticky;
        top: 0;
        left: 0;
        background: #fff;
        margin: 0;
        z-index: 2;
    }
    .z-custom{
        color: $main-color;
        border-color: $main-color;
    }

    // .item{box-shadow: 0 0.0625rem 0.75rem rgb(100 101 102 / 5%);}
    .item-img{width: 3rem;height: 3rem;border-radius: 50%;object-fit: cover;object-position: top;margin-right: 1rem;}
    .item-text-1{font-size: 1.1rem;}
    .item-text-2{color: $color-grey;margin-top: .2rem;}
    .item-btn{min-width: 5rem;}
</style>

