<template>
  <div>
    <scroll-view class="account-area" :scroll-y="true" @scrolltolower="scrolltolower" @scroll="scroll">
      <div class="item">
        <div v-for="(item,index) in processedItems" :key="index" class="item-details"
             :style="'opacity:'+ item.opacity+';width: '+item.width + '%'">
          <div class="item-details-left">
            <div class="item-details-category">
              <span>{{item.category}}</span>
            </div>
          </div>
          <div class="item-details-middle">
            <div class="details-top">
              <div class="item-details-subcategory">
                <span>
                  {{item.subCategory}}
                </span>
              </div>
              <div class="item-details-pay">
              <span :class="'pay-by-'+item.payEn">
                {{item.payCn}}
              </span>
              </div>
            </div>
            <div class="details-bottom">
              <div class="item-details-desc">
              <span>
                {{item.desc}}
              </span>

              </div>
            </div>
          </div>
          <div class="item-details-right">
            <div class="item-details-value">
              <span :class="item.type===0?'out':'in'">{{item.value}}</span>
            </div>
          </div>
        </div>
      </div>
    </scroll-view>
  </div>
</template>

<script>
  import Index from "../../pages/counter/index";
  import {payConfig} from "../../customConfig/payConfig";

  export default {
    components: {Index},

    data () {
      return {
        currentUser: {},

        items: [],
        processedItems: []
      }
    },

    mounted() {
     this.processItemInfo()
    },

    methods: {
      processItemInfo () {
        for(let i=0; i<this.items.length;i++){
          let newItem = {}
          for(let attr in this.items[i]){
            if(attr === 'pay'){
              newItem['payEn'] = this.items[i].pay
              newItem['payCn'] = this.getPayName(this.items[i].pay)
            }
            else if (attr === 'category') {
              newItem[attr] = this.items[i].category.substring(0,1)
            }
            else {
              newItem[attr] = this.items[i][attr]
            }
          }
          newItem.opacity = 1
          newItem.width = 100
          this.processedItems.push(newItem)
        }
      },

      getPayName (pay) {
        for (const payItem of payConfig) {
          if (payItem.payEn === pay) {
            return payItem.payCn
          }
        }
      },

      exitStyle(position) {
        const index = Math.floor(position/80)
        const level = position/80 - index
        for(let i=index;i<index+2;i++){
          if(i === index){
            this.processedItems[i].opacity = 1 - level
            this.processedItems[i].width = (1 - level)*5 + 95
          }
          else{
            this.processedItems[i].opacity = 1
            this.processedItems[i].width = 100
          }
        }
      },

      scrolltolower(){
        console.log('lower')
      },

      scroll(e) {
        this.exitStyle(e.target.scrollTop)
      },
    }
  }
</script>

<style lang="scss">
.account-area {
  height: 85vh;
}
.item {
  width: 90%;
  margin: auto;
  .item-details{
    box-shadow:0 1px 1px rgba(0,0,0,0.11), 0 2px 2px rgba(0,0,0,0.11), 0 4px 4px rgba(0,0,0,0.11), 0 6px 8px rgba(0,0,0,0.11), 0 8px 16px rgba(0,0,0,0.11);
    border-radius: 4px;
    color: #322f3b;
    font-weight: lighter;
    margin: auto auto 5px;
    display: flex;
    flex-direction: row;
    height: 80px;
    transition: width 0.2s;
    .item-details-left {
      width: 15%;
      display: flex;
      flex-direction: row;
      .item-details-category{
        height: 40px;
        width: 40px;
        display: flex;
        flex-direction: column;
        align-self: center;
        background-color: #322f3b;
        font-size: 20px;
        color: #e2e1e4;
        font-weight: bolder;
        border-radius: 99px;
        margin-left: 5px;
        span {
          line-height: 40px;
          text-align: center;
        }
      }
    }

    .item-details-middle{
      width: 60%;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      .details-top{
        height: 30px;
        display: flex;
        flex-direction: row;
        .item-details-subcategory{
          margin-left: 15px;
          padding-top: 10px;
          display: flex;
          flex-direction: column;
          span {
            background-color: #322f3b;
            color: #e2e1e4;
            text-align: center;
            border-radius: 4px;
            font-size: 12px;
            padding: 2px;
            font-weight: bolder;
          }
        }
        .item-details-pay{
          margin-left: 15px;
          padding-top: 10px;
          display: flex;
          flex-direction: column;
          span {
            color: #e2e1e4;
            text-align: center;
            border-radius: 4px;
            font-size: 12px;
            padding: 2px;
            font-weight: bolder;
          }
        }
      }
      .details-bottom{
        height: 50px;
        display: flex;
        flex-direction: row;
        .item-details-desc {
          margin-left: 15px;
          display: flex;
          flex-direction: column;
          align-self: center;
          border: 1px dashed #e2e1e4;
          border-radius: 10px;
          padding: 3px;
          width: 100%;
          span {
            text-align: center;
            font-size: 12px;
          }
        }
      }

    }

    .item-details-right {
      margin-left:auto;
      margin-right: 10px;
      display: flex;
      flex-direction: row;
      .item-details-value {
        display: flex;
        flex-direction: column;
        align-self: center;
        height: 80px;
        span {
          line-height: 80px;
          font-size: 30px;

        }
      }
    }


  }
}

.in {
  color: crimson;
}
.out {
  color: green;
}
.pay-by-wechat {
  background-color: limegreen;
}
.pay-by-bankCard {
  background-color: midnightblue;
}
.pay-by-alipay {
  background-color: dodgerblue;
}
.pay-by-default {
  background-color: #322f3b;
}
</style>
