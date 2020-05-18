<template>
  <div class="edit-container">
    <div class="edit-items">

      <div class="edit-item-val-type" @click="callCalculator" @touchstart="touchStart" @touchend="touchEnd">
        <div :class="accountItem.type === 0 ? 'edit-item-type out':'edit-item-type in'">{{accountItem.type === 0 ? '支出':'收入'}}</div>
        <div :class="accountItem.type === 0 ? 'edit-item-value out':'edit-item-value in'">{{accountItem.value}}</div>
      </div>
      <div class="line"></div>
      <div class="edit-item-cat" @click="callCatSelector">
        <div>{{accountItem.category}}</div>
        <div>></div>
        <div>{{accountItem.subCategory}}</div>
      </div>
      <div class="line"></div>

      <div class="edit-item-cat" @click="callDateSelector">
        <div>{{accountItem.date}}</div>
      </div>
      <div>描述</div>

      <div>支付方式</div>
    </div>


    <div class="cal-root" >
      <calculator @res="getCalRes" v-if="showCalculator"></calculator>
    </div>

    <div v-show="showSelector">
      <scroll-selector  @res="getSelectRes" ref="selector"></scroll-selector>
    </div>

  </div>
</template>

<script>
  import {Category} from "../../customConfig/catConfig";
  import calculator from "../../components/add/calculator";
  import scrollSelector from "../../components/add/scrollSelector";

  export default {
    components: {
      calculator,
      scrollSelector
    },
    data () {
      return {
        selectType:'',
        showCalculator: false,
        showSelector: false,
        accountItem: {
          type: 0,
          value : '0.00',
          category: Category[0].name,
          subCategory: Category[0].subCat[0].name,
          date:''
        },
        touchStartX:null
      }
    },

    mounted () {
     this.getToday()
    },

    methods: {
      getToday () {
        const today = new Date()
        this.accountItem.date = today.getFullYear() + '/' + (today.getMonth()+1) + '/' + today.getDate();
      },

      getCalRes (res){
        this.accountItem.value = res
      },
      callCalculator() {
        this.showCalculator = !this.showCalculator
        if (this.showCalculator) {
          this.showSelector = false
        }
      },

      touchStart (e) {
        this.touchStartX = e.pageX
      },
      touchEnd(e){
        if(Math.abs(e.mp.changedTouches[0].pageX - this.touchStartX) > 50){
          this.accountItem.type = 1 - this.accountItem.type
        }
      },

      callCatSelector() {
        if (this.showSelector) { // 关闭唤醒
          this.showSelector = false
          this.$refs.selector.close()
        } else { // 唤醒
          this.showSelector = true
          this.showCalculator = false
          this.selectType = 'cat'
          this.$refs.selector.colCount = 2
          this.$refs.selector.cols= [Category,Category[0].subCat]
          this.$refs.selector.open()
          this.$refs.selector.type = 'custom'
        }
      },
      callDateSelector() {
        if (this.showSelector) { // 关闭唤醒
          this.showSelector = false
          this.$refs.selector.close()
        } else { // 唤醒
          this.showSelector = true
          this.showCalculator = false
          this.selectType = 'date'
          this.$refs.selector.dateMode(this.accountItem.date.split('/'))
          this.$refs.selector.open()

        }
      },

      getSelectRes (res, type) {
        if (this.selectType === 'cat' && type === 'custom') {
          this.accountItem.category = Category[res[0]].name
          this.accountItem.subCategory = Category[res[0]].subCat[res[1]].name
          this.$refs.selector.cols.splice(1, 1, Category[res[0]].subCat) // array.splice(indexOfItem, 1, newValue)
          //this.$refs.selector.cols[1]= Category[res[0]].subCat
        }
        else if (this.selectType === 'date' && type === 'date') {
          this.accountItem.date = (2010 + res[0])+'/'+(1+res[1])+'/'+ (1+res[2])
        }
      }


    }
  }
</script>

<style lang="scss">
.line {
  margin: 20px;
  border-bottom: 1px solid #cccccc;
  box-shadow:0 1px 1px rgba(0,0,0,0.11), 0 2px 2px rgba(0,0,0,0.11), 0 4px 4px rgba(0,0,0,0.11), 0 6px 8px rgba(0,0,0,0.11), 0 8px 16px rgba(0,0,0,0.11);
}
.edit-container{
  padding: 10px;
  display: flex;
  min-height: 100vh;
  flex-direction: column;
  .edit-items {
    flex: 1 0 auto;
    border-radius: 4px;
    padding: 5px;
  }
  .cal-root {
    flex:0 0 auto;
    margin-bottom: 20px;
  }
}
.edit-item-val-type {
  margin-top: 10px;
  border: 1px solid white;
  border-radius: 4px;
  height: 50px;
  line-height: 50px;
  font-size: 30px;
  font-weight: lighter;
  display: flex;
  .edit-item-type {
    font-size: 12px;
    margin-left: 10px;
  }
  .edit-item-value {
    text-align: center;
    translate: -8%;
    flex: 1;
  }

}
.in {
  color: crimson;
}
.out {
  color: green;
}
.edit-item-cat {
  margin-top: 10px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  :first-child {
    font-weight: bolder;
    color: #322f3b;
    border: 1px solid white;
    border-radius: 4px;
  }
  :last-child {
    font-weight: bolder;
    color: #322f3b;
    border: 1px solid white;
    border-radius: 4px;
  }
  div {
    font-weight: bolder;
    padding: 5px;
    border-radius: 4px;
    width: 30%;
    text-align: center;
    height: 30px;
    line-height: 30px;
  }
}
.picker-view {
  width: 100%;
  height: 300px;
}
.picker-view-col {
  line-height: 50px;
  text-align: center;
}

</style>
