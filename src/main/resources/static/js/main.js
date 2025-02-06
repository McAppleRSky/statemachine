const { createApp, ref } = Vue
createApp({
    template: `
<header></header>
<aside>
  <div>
    <select v-model="catalogSelected" @change="onChangeCatalogItem()">
      <option value=""/>
      <option v-for="catalogItem in catalog" :value="catalogItem.name">{{catalogItem.title}}</option>
    </select>
  </div>
  <div>
    <label v-for="workObjectItem in workObject" :for="workObjectItem.name">
      <input type="radio" :id="workObjectItem.name" :value="workObjectItem.name" @input="onInputWorkObjectItem">
      {{ workObjectItem.title }}
    </label>
  </div>
</aside>
<main>
  <table v-for="table of data.content" border="1">
    <caption style="caption-side: top">{{table.aCaption}}</caption>
    <thead v-for="thead of table.bHead">
      <tr v-for="trh of thead.aRowh">
        <th v-for="(th, i) in trh.column" :key="i" style="color: transparent"
            :ref="(el)=>{if(i%2){reference.stateSecEl.push(el)}}"
        >{{ i }}</th>
      </tr>
      <tr v-for="trd of thead.bRowd" style="border-width: 1px">
        <td v-for="state in trd.state" :colspan="state.colSpan" style="text-align: center"
            :ref="(el)=>{reference.tdState.push({td:el,name:state.title})}"
        >{{ state.title }}</td>
      </tr>
    </thead>
    <tbody v-for="tbody of table.cBody">
      <tr v-for="trArrow in tbody.aRowArrow">
        <td v-for="tdArrow in trArrow.cellArrow"
            :ref="(el)=>{if(tdArrow.hasOwnProperty('canvas')){let tempCanvas=reference.tempCanvas.pop();console.log('tempCanvas ' + tempCanvas);console.dir(tempCanvas);reference.tdArrow.push({td:el,stateTitle:tempCanvas.arrow,reverse:tempCanvas.reverse})}}"
            :colspan="tdArrow.colSpan"
            :class="{cellBorderRight:tdArrow.borderRight===true,cellBorderLeft:tdArrow.borderLeft===true,firstCell:tdArrow.isFirstCell===true}" >
          <canvas v-if="tdArrow.canvas === true" class="arrow"
                  :ref="(el)=>{reference.tempCanvas.push({arrow:tdArrow.arrow,reverse:tdArrow.reverse})}"
          >
            <!--:ref="(el)=>{if(tdArrow.hasOwnProperty('canvas')){let tempCanvas=reference.tempCanvas.pop();reference.tdArrow.push({td:el,toStateName:tempCanvas.to,fromStateName:tempCanvas.from})}}"-->
                  <!--:ref="(el)=>{reference.tempCanvas.push({from:tdArrow.arrow[0],to:tdArrow.arrow[1]})}"-->
          </canvas>
        </td>
      </tr>
      <tr v-for="trTitle in tbody.rowTitle">
        <td v-for="tdTitle in trTitle.cellTitle" :colspan="tdTitle.colSpan" :class="{cellBorderRight: tdTitle.borderRight === true, cellBorderLeft: tdTitle.borderLeft === true, firstCell: tdTitle.isFirstCell === true}">
          {{ tdTitle.title }}
        </td>
      </tr>
      <tr v-for="trDesc in tbody.rowDesc">
        <td v-for="tdDesc in trDesc.cellDesc" :colspan="tdDesc.colSpan" :class="{cellBorderRight: tdDesc.borderRight === true, cellBorderLeft: tdDesc.borderLeft === true, firstCell: tdDesc.isFirstCell === true}">
          <pre v-if="tdDesc.description">{{ tdDesc.description }}</pre>
        </td>
      </tr>
    </tbody>
  </table>
</main>
<footer></footer>`,
    data() {
        return {
            catalog: [],
            catalogSelected: null,
            workObject: [],
            reference: {
                tdState: [],
                tdArrow: [],
                tempCanvas: [],
                stateSecEl:[],
                rowEl:[],
            },
            data: {
                content: [/*{
            caption: "State transition mapping for work object : wSpace",
            head: [{
              rowh: [{
                column: [{}, {}, {}, {}, {}, {}, {}, {}],
              }],
              rowd: [
                {state: [{title: "NULL", colSpan: 2}, {title: "wDraft", colSpan: 2}, {title: "wActive", colSpan: 2}, {title: "wRetired", colSpan: 2}]} ]
            }],
            body: [
              {
                rowArrow: [{
                  cellArrow:[{borderRight:true, isFirstCell:true}, {arrow:["NULL","wDraft"], colSpan: 2, borderLeft:true, borderRight:true, canvas:true}, {borderLeft:true}, {borderRight:true}, {borderLeft:true}, {borderRight:true}, {borderLeft:true}]
                }],
                rowTitle: [{
                  cellTitle: [{borderRight:true, isFirstCell:true}, {title: "wCreateDraft", colSpan: 2}, {borderLeft:true}, {borderRight:true}, {borderLeft:true}, {borderRight:true}, {borderLeft:true}]
                }],
                rowDesc: [{
                  cellDesc: [{borderRight:true, isFirstCell:true}, {description: "a\nb", colSpan: 2}, {borderLeft:true}, {borderRight:true}, {borderLeft:true}, {borderRight:true}, {borderLeft:true}]
                }]
              },
              {
                rowArrow: [{
                  cellArrow:[{borderRight:true, isFirstCell:true}, {borderLeft:true}, {borderRight:true}, {arrow:["wDraft","wDraft"], colSpan: 2, canvas:true}, {borderLeft:true}, {borderRight:true}, {borderLeft:true}]
                }],
                rowTitle: [{
                  cellTitle: [{borderRight:true, isFirstCell:true}, {borderLeft:true}, {borderRight:true}, {title: "wAction", colSpan: 2}, {borderLeft:true}, {borderRight:true}, {borderLeft:true}]
                }],
                rowDesc: [{
                  cellDesc: [{borderRight:true, isFirstCell:true}, {borderLeft:true}, {borderRight:true}, {description: "ab\nbc", colSpan: 2}, {borderLeft:true}, {borderRight:true}, {borderLeft:true}]
                }]
              },
              {
                rowArrow: [{
                  cellArrow:[{borderRight:true, isFirstCell:true}, {borderLeft:true}, {borderRight:true}, {arrow:["wDraft","wRetired"], colSpan: 4, canvas:true}, {borderLeft:true}]
                }],
                rowTitle: [{
                  cellTitle: [{borderRight:true, isFirstCell:true}, {borderLeft:true}, {borderRight:true}, {title: "wRetire", colSpan: 2}, {borderLeft:true}, {borderRight:true}, {borderLeft:true}]
                }],
                rowDesc: [{
                  cellDesc: [{borderRight:true, isFirstCell:true}, {borderLeft:true}, {borderRight:true}, {description: "abc\nxyz", colSpan: 2}, {borderLeft:true}, {borderRight:true}, {borderLeft:true}]
                }]
              }]
          }*/]
            },
            arrow: {
                start: null,
                end: null,
                action: ''
            },
            refTh: {
                empty: null,
                ref: []
            },
            arrElBegEnd: [],
            arrElBegEndCount: 1,
            arrElBegEndFillComplete: false
        }
    },
    created: function () {
        let request = new XMLHttpRequest();
        request.open("GET", "/api/0.0.1/catalog", true);
        request.onload = () => {
            if (this.catalog.length > 0) {
                this.catalog = []
            }
            for (let catalogItemResponse of JSON.parse(request.response).catalog) {
                this.catalog.push(catalogItemResponse)
            }
        }
        request.send()
    },
    computed: {
    },
    methods: {
        onChangeCatalogItem: function(value) {
            if (this.workObject !== null && this.workObject !== '') {
                let request = new XMLHttpRequest();
                request.open("GET", "/api/0.0.1/work-object/" + this.catalogSelected, true);
                request.onload = () => {
                    if (this.workObject.length > 0) {
                        this.workObject = []
                    }
                    let resp = JSON.parse(request.response);
                    if (resp.hasOwnProperty(this.catalogSelected)) {
                        for (let workObj of resp[this.catalogSelected]) {
                            this.workObject.push(workObj)
                        }
                    }
                }
                request.send()
            }

        },
        onInputWorkObjectItem: function(eTargetWoName) {
            let request = new XMLHttpRequest();
            request.open("GET", "/api/0.0.1/state-transition/" + eTargetWoName.target.value, true);
            request.onload = () => {
                let templ = respStateTransitTempl(eTargetWoName.target.value, request);
                if (templ != null) {
                    this.data.content.push(templ)
                }
                console.log("onInputWorkObjectItem data.content " + this.data.content);
                console.dir(this.data.content)
                // console.dir(this.catalog)
            }
            request.send()
        },
        /*getWoObject: function() {
            let request = new XMLHttpRequest();
            request.open("GET", "/api/0.0.1/state-transition/wPeople", true);
            request.onload = () => {
                let templ = respTempl("wPeople", request);
                if (templ != null) {
                    this.data.content.push(templ)
                }
            }
            request.send()
        },*/
        onUpdatedPreFormat: function() {
            if (this.data.content.length > 0) {
                $(".firstCell").css({width: this.reference.tdState[0].td.offsetWidth / 2})
            }
        },
        onUpdatedHasArrow: function() {
            if (this.data.content.length > 0) {
                console.log("onUpdatedHasArrow this.reference.tdArrow " + this.reference.tdArrow);
                console.dir(this.reference);
                for (let arrow of this.reference.tdArrow) {
                    let direct = 0, pos = [];
                    if (arrow.stateTitle.length === 2 /*&& arrow[0] !== arrow[1]*/) {
                        direct = arrow.reverse === true ? -1 : 1;
                        /*console.log("arrow.stateTitle " + arrow.stateTitle[0] + ' ' + arrow.stateTitle[1]);
                        for (let i = 0; i < this.data.content[0].bHead[0].bRowd[0].state.length; i++) {
                            if (arrow.stateTitle[0] === this.data.content[0].bHead[0].bRowd[0].state[i].title) {
                                pos.push(i)
                            } else {
                                if (arrow.stateTitle[1] === this.data.content[0].bHead[0].bRowd[0].state[i].title) {
                                    pos.push(i)
                                }
                            }

                        }
                        console.log("pos " + pos[0] + ' ' + pos[1]);
                        direct = pos[1] - pos[0]*/
                    }
                    this.drawArrow(direct, arrow.td.firstChild)
                }
            }
        },
        drawArrow(direct, canvas) {
            let drawPlainArrow = function (beginX, endX, rowY, canvas){
                let canvasContext = canvas.getContext("2d");
                canvasContext.fillStyle = 'steelblue';
                canvasContext.strokeStyle = 'steelblue';
                // draw line from start to end
                canvasContext.beginPath();
                canvasContext.moveTo(beginX, rowY);
                canvasContext.lineTo(endX, rowY);
                canvasContext.lineWidth = 2;
                canvasContext.stroke();
                // draw circle at beginning of line
                canvasContext.beginPath();
                canvasContext.arc(beginX, rowY, 4, 0, Math.PI * 2, true);
                canvasContext.fill();
                // draw pointer at end of line (needs rotation)
                canvasContext.beginPath();
                let angle = Math.atan2(0, endX - beginX);
                canvasContext.translate(endX, rowY);
                canvasContext.rotate(angle);
                canvasContext.moveTo(0, 0);
                canvasContext.lineTo(-10, -7);
                canvasContext.lineTo(-10, 7);
                canvasContext.lineTo(0, 0);
                canvasContext.fill()
            }
            let beginX, endX, rowY = canvas.clientHeight / 2;
            if (direct === 0) {
                beginX = canvas.width / 2;
                endX = 0
            } else {
                if (direct > 0) {
                    beginX = 0;
                    endX = canvas.width
                } else {
                    beginX = canvas.width;
                    endX = 0
                }
            }
            // drawPlainArrow(direct ? 0 : canvas.width / 2, direct ? canvas.width : 0, canvas.clientHeight / 2, canvas);
            console.log("direct " + direct);
            console.log("drawPlainArrow " + beginX + ' ' + endX  + ' ' + rowY);
            drawPlainArrow(beginX, endX, rowY, canvas)
        }
    },
    beforeMount() {
        console.log("beforeMount");
    },
    beforeUpdated() {
        console.log("beforeUpdated");
    },
    updated() {
        console.log("updated");
        this.onUpdatedHasArrow();
        this.onUpdatedPreFormat();
    },
    mounted() {
        // console.log("Front mounted");
        // alert("mounted");
        console.log("catalogSelected " + this.catalogSelected);
        // let content = JSON.stringify(this.data.content[0]);
        // console.log(content);
        /*$(".firstCell").css({width: this.reference.tdState[0].td.offsetWidth/2});
        for (let arrow of this.reference.tdArrow) {
            let direct = true;
            if (arrow.toStateName === arrow.fromStateName) {
                direct = false
            }
            arrow.td.firstChild.height = arrow.td.parentElement.clientHeight;
            this.onMountedDrawArrow(direct, arrow.td.firstChild)
        }*/
    }
}).mount('#app')
