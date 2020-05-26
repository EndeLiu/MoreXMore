<template>
  <div>
    <picker-view :value="value" indicator-style="height: 50px;" indicator-class="picker-view-indicator" class="picker-view"  @change="bindChange">
      <picker-view-column class="picker-view-col" v-for="(col,colIndex) in cols" :key="colIndex">
        <view v-for="(item, index) in col" :key="index" >{{item.name}}</view>
      </picker-view-column>
    </picker-view>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        value:[0,0],
        colCount: 2,
        cols:[],
        type:'custom',
        closeFlag: true // true:不接受change事件
      }
    },


    methods: {
      open () {
        this.closeFlag = false
        this.$emit('res', this.value, this.type)
      },
      close () {
        this.closeFlag = true
      },

      dateMode (defaultDate) {
        this.type = 'date'
        const yearLimit = defaultDate === null ? 2050 : Number(defaultDate[0])
        const monthLimit = defaultDate === null ? 12 : Number(defaultDate[1])
        const dayLimit = defaultDate === null ? 31 : Number(defaultDate[2])

        let years = []
        let yearNum = []
        for (let i = 2010; i < yearLimit + 1; i++) {
          yearNum.push(i)
          years.push({name:i})
        }

        let months = []
        let monthNum = []
        for (let i = 0; i < monthLimit; i++) {
          monthNum.push(i+1)
          months.push({name:i+1})
        }

        let days = []
        let dayNum = []
        for (let i = 0; i < dayLimit; i++) {
          dayNum.push(i+1)
          days.push({name:i+1})
        }
        this.colCount = 3
        this.cols = [years, months, days]

        if (defaultDate === null) {
          const today = new Date()
          this.value = [yearNum.indexOf(today.getFullYear()),monthNum.indexOf(today.getMonth()+1),dayNum.indexOf(today.getDate())]
        } else {
          this.value = [
            yearNum.indexOf(parseInt(defaultDate[0])),
            monthNum.indexOf(parseInt(defaultDate[1])),
            dayNum.indexOf(parseInt(defaultDate[2]))
          ]
        }
      },

      dateSelect (dateIndex) {
        const today = new Date()
        const bigMonth = [1, 3, 5, 7, 8, 10, 12]
        const smallMonth = [4, 6, 9, 11]

        // 根据是否为当前年份，对可选的月份进行限制，不允许选择当前年当前月份之后的日期
        let months = []
        for (let i = 0; i < 12; i++) {
          months.push({name: i + 1})
        }
        if (dateIndex[0] === this.cols[0].length - 1) {
          const currentMonth = today.getMonth() + 1
          this.cols.splice(1, 1, months.slice(0, currentMonth))
        }
        else {
          this.cols.splice(1, 1, months.slice(0, 12))
        }

        let days = []
        for (let i = 0; i < 31; i++) {
          days.push({name: i + 1})
        }

        // 数据的处理，防止索引超出可选长度
        for (let i = 0; i < this.cols.length; i++) {
          if (dateIndex[i] > this.cols[i].length - 1) {
            dateIndex[i] = this.cols[i].length - 1
          }
        }


        // array.splice(indexOfItem, 1, newValue)
        // 当前月为小月， 日选择列切成30
        if (smallMonth.indexOf(this.cols[1][dateIndex[1]].name) !== -1) {
          this.cols.splice(2, 1, days.slice(0,30))
        }
        // 当前月为大月， 日选择列为31
        else if (bigMonth.indexOf(this.cols[1][dateIndex[1]].name) !== -1) {
          this.cols.splice(2, 1, days)
        }
        else {
          if (this.cols[0][dateIndex[0]].name % 4 === 0) {
            this.cols.splice(2, 1, days.slice(0,29))
          } else {
            this.cols.splice(2, 1, days.slice(0,28))
          }
        }
        //if (dateIndex[1] === this.cols[1].length - 1) { // 当前选择月份为实际当前月份，对日期进行限制
        if (dateIndex[0] === this.cols[0].length - 1 && dateIndex[1] === this.cols[1].length - 1) {
          this.cols.splice(2, 1, days.slice(0, today.getDate()))
        }
      },

      bindChange(e) {
        if (!this.closeFlag) {
          const indexArray =  e.target.value
          // value 的 赋值逻辑
          for (let col=0;col<indexArray.length;col++) {
            if (this.value[col] !== e.target.value[col]) { // 当前列产生变化，其他后续列，重置为0索引,value结束
              this.value[col] = indexArray[col]
              for(let i=col+1;i<indexArray.length;i++) {
                this.value[i] = 0
              }
              break
            }
          }

          // value的提交逻辑
          if (this.type === 'date') {
            this.dateSelect(indexArray)
            this.$emit('res', this.value, this.type)
          }
          else if (this.type === 'custom') {
            this.$emit('res', this.value, this.type)
          }
        }
      }
    }
  }
</script>

<style lang="scss">
.picker-view-indicator {
  color: #322f3b;
  border-bottom: 1px solid rgba(0, 0, 0, 0.11);
}
.picker-view-col {
  background-color: white;
}
</style>
