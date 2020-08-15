
<template>
    <div class="box rel">
        <div class="abs left-box sel-box">
            <span
                v-for="(item, index) in sort.sexStatus"
                :key="index"
                v-show="item.show"
                :class="{cur: params.sex === item.value}"
                @click="setStatus(item)"
            >{{item.text}}</span>
        </div>

        <div class="abs right-box sel-box">
            <span
                v-for="(item, index) in sort.ageStatus"
                :key="index"
                v-show="item.show"
                :class="{cur: params.age === item.value}"
                @click="setStatus(item)"
            >{{item.text}}</span>
        </div>

        <div class="abs bottom-box">
            <span
                v-for="(item, index) in sort.pnStatus"
                :key="index"
                v-show="item.show"
                @click="setStatus(item)"
                class="abs"
                :style="{'z-index': params.pn === item.value ? 1 : 2}"
            >{{item.text}}</span>
        </div>

        <div class="rel area-box">
            <img usemap="#img" class="main-img" :src="imgUrl" alt ref="img" />
        </div>

        <map name="img">
            <area
                v-for="(item, index) in partList"
                :key="index"
                shape="rect"
                :coords="setCoords(item)"
                @click="areaClick(item)"
            />
        </map>
    </div>
</template>

<script lang="ts">
    import {Component, Vue, Inject} from 'vue-property-decorator';


    @Component
    export default class Intelligent extends Vue {
        @Inject('igIndex') readonly igIndex!: IOBJ;
        data: IOBJ = {
            // 男人正
            drP: {
                imgUrl: require('@/assets/image/intelguide/dr1.png'),
                partList: [
                    {part: '耳', value: [159, 62, 173, 90]},
                    {part: '耳', value: [220, 64, 234, 92]},
                    {part: '眼', value: [176, 62, 218, 74]},
                    {part: '口', value: [183, 91, 210, 103]},
                    {part: '鼻', value: [192, 72, 204, 89]},
                    {part: '头部', value: [159, 17, 234, 118]},

                    {part: '颈部', value: [173, 113, 220, 134]},

                    {part: '胸部', value: [128, 136, 264, 212]},
                    {part: '腹部', value: [135, 216, 253, 324]},
                    // 左上肢
                    {part: '上肢', value: [95, 138, 133, 268]},
                    {part: '上肢', value: [72, 263, 125, 32]},
                    {part: '上肢', value: [50, 319, 103, 378]},
                    {part: '上肢', value: [27, 352, 82, 431]}, // 手
                    // 右上肢
                    {part: '上肢', value: [258, 132, 303, 269]},
                    {part: '上肢', value: [269, 269, 323, 332]},
                    {part: '上肢', value: [290, 306, 344, 369]},
                    {part: '上肢', value: [311, 353, 366, 428]}, // 手
                    // 臀部
                    {part: '生殖器', value: [124, 329, 264, 395]},
                    // 左下肢
                    {part: '下肢', value: [122, 391, 190, 722]},
                    // 右下肢
                    {part: '下肢', value: [188, 391, 256, 722]},
                ]
            },
            // 男人反
            drN: {
                imgUrl: require('@/assets/image/intelguide/dr2.png'),
                partList: [
                    {part: '头部', value: [151, 17, 230, 106]},

                    {part: '颈部', value: [158, 105, 220, 121]},

                    {part: '背部', value: [132, 122, 258, 311]},
                    // 左上肢
                    {part: '上肢', value: [95, 138, 133, 268]},
                    {part: '上肢', value: [72, 263, 125, 32]},
                    {part: '上肢', value: [50, 319, 103, 378]},
                    {part: '上肢', value: [27, 352, 82, 431]}, // 手
                    // 右上肢
                    {part: '上肢', value: [258, 132, 303, 269]},
                    {part: '上肢', value: [269, 269, 323, 332]},
                    {part: '上肢', value: [290, 306, 344, 369]},
                    {part: '上肢', value: [311, 353, 366, 428]}, // 手
                    // 臀部
                    {part: '臀部', value: [124, 329, 264, 395]},
                    // 左下肢
                    {part: '下肢', value: [117, 389, 190, 731]},
                    // 右下肢
                    {part: '下肢', value: [192, 391, 265, 733]},
                ]
            },
            // 女人正
            nrP: {
                imgUrl: require('@/assets/image/intelguide/nr1.png'),
                partList: [
                    {part: '耳', value: [160, 65, 170, 85]},
                    {part: '耳', value: [219, 61, 230, 88]},
                    {part: '眼', value: [172, 62, 217, 74]},
                    {part: '口', value: [184, 88, 203, 99]},
                    {part: '鼻', value: [188, 66, 200, 88]},
                    {part: '头部', value: [160, 11, 231, 112]},

                    {part: '颈部', value: [174, 110, 213, 133]},

                    {part: '胸部', value: [141, 137, 245, 226]},
                    {part: '腹部', value: [143, 224, 247, 313]},
                    // 左上肢
                    {part: '上肢', value: [105, 133, 142, 269]},
                    {part: '上肢', value: [94, 270, 129, 323]},
                    {part: '上肢', value: [51, 319, 108, 398]}, // 手
                    // 右上肢
                    {part: '上肢', value: [243, 134, 284, 276]},
                    {part: '上肢', value: [258, 269, 297, 322]},
                    {part: '上肢', value: [277, 320, 336, 397]}, // 手
                    // 臀部
                    {part: '生殖器', value: [130, 308, 258, 369]},
                    // 左下肢
                    {part: '下肢', value: [130, 371, 194, 730]},
                    // 右下肢
                    {part: '下肢', value: [195, 369, 259, 728]},
                ]
            },
            // 女人反
            nrN: {
                imgUrl: require('@/assets/image/intelguide/nr2.png'),
                partList: [
                    {part: '头部', value: [157, 18, 231, 103]},

                    {part: '颈部', value: [172, 103, 215, 130]},

                    {part: '背部', value: [144, 130, 247, 298]},
                    // 左上肢
                    {part: '上肢', value: [113, 130, 144, 249]},
                    {part: '上肢', value: [98, 247, 135, 295]},
                    {part: '上肢', value: [85, 296, 122, 334]},
                    {part: '上肢', value: [51, 322, 110, 403]}, // 手
                    // 右上肢
                    {part: '上肢', value: [241, 136, 285, 265]},
                    {part: '上肢', value: [252, 255, 296, 299]},
                    {part: '上肢', value: [264, 297, 307, 325]},
                    {part: '上肢', value: [274, 325, 333, 403]}, // 手
                    // 臀部
                    {part: '臀部', value: [130, 299, 260, 363]},
                    // 左下肢
                    {part: '下肢', value: [128, 368, 193, 731]},
                    // 右下肢
                    {part: '下肢', value: [194, 365, 259, 728]},
                ]
            },
            // 男孩正
            nhP: {
                imgUrl: require('@/assets/image/intelguide/nh1.png'),
                partList: [
                    {part: '耳', value: [107, 129, 137, 178]},
                    {part: '耳', value: [233, 127, 261, 174]},
                    {part: '眼', value: [146, 112, 231, 153]},
                    {part: '口', value: [161, 173, 213, 192]},
                    {part: '鼻', value: [176, 153, 192, 176]},
                    {part: '头部', value: [103, 28, 269, 212]},

                    {part: '颈部', value: [133, 207, 235, 222]},

                    {part: '胸部', value: [119, 222, 258, 323]},
                    {part: '腹部', value: [118, 318, 259, 416]},
                    // 左上肢
                    {part: '上肢', value: [69, 223, 118, 362]},
                    {part: '上肢', value: [49, 359, 106, 423]},
                    {part: '上肢', value: [24, 413, 91, 483]}, // 手
                    // 右上肢
                    {part: '上肢', value: [255, 226, 312, 380]},
                    {part: '上肢', value: [270, 378, 320, 440]},
                    {part: '上肢', value: [277, 436, 329, 488]}, // 手
                    // 臀部
                    {part: '生殖器', value: [106, 410, 272, 499]},
                    // 左下肢
                    {part: '下肢', value: [106, 499, 188, 725]},
                    // 右下肢
                    {part: '下肢', value: [189, 500, 278, 725]},
                ]
            },
            // 男孩反
            nhN: {
                imgUrl: require('@/assets/image/intelguide/nh2.png'),
                partList: [
                    {part: '头部', value: [108, 31, 272, 206]},

                    {part: '颈部', value: [108, 206, 274, 216]},

                    {part: '背部', value: [116, 216, 257, 398]},
                    // 左上肢
                    {part: '上肢', value: [64, 230, 114, 386]},
                    {part: '上肢', value: [53, 386, 106, 425]},
                    {part: '上肢', value: [33, 421, 93, 476]}, // 手
                    // 右上肢
                    {part: '上肢', value: [255, 224, 317, 369]},
                    {part: '上肢', value: [269, 369, 331, 410]},
                    {part: '上肢', value: [289, 409, 356, 470]}, // 手
                    // 臀部
                    {part: '臀部', value: [110, 397, 263, 501]},
                    // 左下肢
                    {part: '下肢', value: [94, 499, 185, 726]},
                    // 右下肢
                    {part: '下肢', value: [187, 500, 278, 727]},
                ]
            },
            // 女孩正
            nhzP: {
                imgUrl: require('@/assets/image/intelguide/nhz1.png'),
                partList: [
                    {part: '耳', value: [104, 134, 134, 181]},
                    {part: '耳', value: [232, 133, 259, 177]},
                    {part: '眼', value: [140, 121, 228, 158]},
                    {part: '口', value: [154, 178, 209, 202]},
                    {part: '鼻', value: [174, 159, 191, 179]},
                    {part: '头部', value: [26, 25, 348, 218]},

                    // {part: '颈部', value: [173, 113, 220, 134]},

                    {part: '胸部', value: [109, 219, 257, 337]},
                    {part: '腹部', value: [109, 337, 257, 420]},
                    // 左上肢
                    {part: '上肢', value: [65, 227, 117, 351]},
                    {part: '上肢', value: [47, 350, 107, 410]},
                    {part: '上肢', value: [27, 410, 85, 484]}, // 手
                    // 右上肢
                    {part: '上肢', value: [255, 233, 301, 351]},
                    {part: '上肢', value: [265, 350, 320, 439]},
                    {part: '上肢', value: [277, 439, 322, 491]}, // 手
                    // 臀部
                    {part: '生殖器', value: [109, 421, 262, 482]},
                    // 左下肢
                    {part: '下肢', value: [109, 484, 186, 728]},
                    // 右下肢
                    {part: '下肢', value: [186, 485, 275, 725]},
                ]
            },
            // 女孩反
            nhzN: {
                imgUrl: require('@/assets/image/intelguide/nhz2.png'),
                partList: [
                    {part: '头部', value: [29, 27, 337, 214]},

                    // {part: '颈部', value: [158, 105, 220, 121]},

                    {part: '背部', value: [114, 215, 254, 424]},
                    // 左上肢
                    {part: '上肢', value: [69, 228, 114, 350]},
                    {part: '上肢', value: [55, 350, 107, 396]},
                    {part: '上肢', value: [33, 395, 95, 481]}, // 手
                    // 右上肢
                    {part: '上肢', value: [253, 228, 310, 383]},
                    {part: '上肢', value: [271, 383, 330, 418]},
                    {part: '上肢', value: [285, 418, 349, 476]}, // 手
                    // 臀部
                    {part: '臀部', value: [103, 424, 266, 502]},
                    // 左下肢
                    {part: '下肢', value: [91, 503, 184, 727]},
                    // 右下肢
                    {part: '下肢', value: [186, 503, 260, 725]},
                ]
            },
        };
        sort: IOBJ = {
            sexStatus: [
                {text: '男', value: 1, key: 'sex', show: true},
                {text: '女', value: 0, key: 'sex', show: true},
            ],
            ageStatus: [
                {text: '成年', value: 1, key: 'age', show: true},
                {text: '儿童', value: 0, key: 'age', show: true},
            ],
            pnStatus: [
                {text: '转身', value: 1, key: 'pn', show: true},
                {text: '转身', value: 0, key: 'pn', show: true},
            ],
        };
        imgUrl: string = '';
        partList: IOBJ[] = [];
        params: IOBJ = {
            // 1男 0女
            sex: 1,
            // 1成年 0儿童
            age: 1,
            // 1正 0反
            pn: 1,
        };

        loadImg (imgUrl: string) {
            return new Promise((rel, rej) => {
                const img = new Image();
                img.src = imgUrl;

                img.onload = () => rel();
                img.onerror = () => rej();
            });
        }

        renderImg (imgUrl: string) {
            return new Promise((rel) => {
                this.imgUrl = imgUrl;
                this.$nextTick(() => {
                    rel();
                });
            });
        }

        remderPart (partList: IOBJ[]) {
            return new Promise((rel) => {
                this.partList = partList;
                this.$nextTick(() => {
                    rel();
                });
            });
        }

        setCoords (item: IOBJ) {
            const standardWidth = 398;
            const imgWidth = (this.$refs.img as IOBJ).offsetWidth;
            const proportion = imgWidth / standardWidth;
            const arr = item.value;
            // console.log(imgWidth, standardWidth, proportion);
            return arr.map((num: number) => (num * proportion)).join();
        }

        sleep (d = 1000) {
            return new Promise((rel) => {
                setTimeout(rel, d);
            });
        }

        async startLoadImg (data: IOBJ) {
            utils.showLoad();
            const {imgUrl, partList} = data;
            await this.loadImg(imgUrl);
            await this.renderImg(imgUrl);
            // 怕没渲染完成
            await this.sleep(200);
            await this.remderPart(partList);
            utils.hideLoad();
        }

        setStatus (item?: IOBJ) {
            if (item) {
                const {value, key} = item;
                this.params[key] = value;
            }
            const {sex, age, pn} = this.params;
            let key = '';

            switch (true) {
                case sex === 1 && age === 1:
                    key = 'dr';
                    break;
                case sex === 0 && age === 1:
                    key = 'nr';
                    break;
                case sex === 1 && age === 0:
                    key = 'nh';
                    break;
                case sex === 0 && age === 0:
                    key = 'nhz';
                    break;
            }

            key += `${pn === 1 ? 'P' : 'N'}`;
            this.startLoadImg(this.data[key]);
        }

        areaClick (item: IOBJ) {
            // console.log(this.igIndex);
            this.igIndex.openFun(item);
        }

        created () {
            // this.showData = this.data.drP;
            // console.log(this);
        }

        async mounted () {
            this.$nextTick(() => {
                this.setStatus();
            });
        }
    }
</script>

<style lang="scss" scoped>
    // $main-color: #2298f1;

    .box {
        height: 100%;
    }
    .area-box {
        height: 100%;
    }
    .main-img {
        margin: auto;
        height: 100%;
        display: block;
        // max-width: 100%;
        // object-fit: contain;
        // width: 100%;
    }

    .sel-box {
        background: #ddd;
        text-align: center;
        padding: 0.2rem;
        border-radius: 1.5rem;
        font-size: 0.9rem;
        z-index: 1;
        line-height: 1.5;
        top: 0.8rem;
    }

    .sel-box > span {
        padding: 0.1rem 0.4rem;
        min-width: 2rem;
        display: inline-block;
    }

    .cur {
        background: $main-color;
        border-radius: 1.5em;
        color: #fff;
    }

    .right-box {
        right: 0.8rem;
    }
    .left-box {
        left: 0.8rem;
    }

    .bottom-box{
        background: $main-color;
        width: 2.6rem;
        line-height: 1.5;
        height: 2.6rem;
        line-height: 2.6rem;
        text-align: center;
        bottom: 1.5rem;
        right: 1.5rem;
        padding: .5rem;
        color: #fff;
        border-radius: 50%;
        z-index: 1;
        font-size: .9rem;
    }

    .bottom-box>span{
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
    }
</style>

