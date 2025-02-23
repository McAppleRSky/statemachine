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
        <th class="thState" v-for="(th, i) in trh.column" :key="i" style="color: transparent"
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
            :ref="(el)=>{if(tdArrow.hasOwnProperty('canvas')){let tempCanvas=reference.tempCanvas.pop();reference.tdArrow.push({td:el,stateTitle:tempCanvas.arrow,reverse:tempCanvas.reverse})}}"
            :colspan="tdArrow.colSpan"
            :class="{cellBorderRight:tdArrow.borderRight===true,cellBorderLeft:tdArrow.borderLeft===true,firstCell:tdArrow.isFirstCell===true}" >
          <canvas v-if="tdArrow.canvas === true" class="arrow"
                  :ref="(el)=>{reference.tempCanvas.push({arrow:tdArrow.arrow,reverse:tdArrow.reverse})}"
          >
          </canvas>
        </td>
      </tr>
      <tr v-for="trTransit in tbody.bRowTransit">
        <td v-for="tdTitle in trTransit.cellTransit" :colspan="tdTitle.colSpan" :class="{cellBorderRight: tdTitle.borderRight === true, cellBorderLeft: tdTitle.borderLeft === true, firstCell: tdTitle.isFirstCell === true}">
          {{ tdTitle.label }}
          <div><dl v-if="tdTitle.hasOwnProperty('name')"><dt>name:</dt><dd>&nbsp;{{ tdTitle.name }}</dd></dl>
          <dl v-if="tdTitle.hasOwnProperty('currentState')"><dt>State current:</dt><dd>&nbsp;{{ tdTitle.currentState }}</dd></dl>
          <dl v-if="tdTitle.hasOwnProperty('nextState')"><dt>State next:</dt><dd>&nbsp;{{ tdTitle.nextState }}</dd></dl>
          <dl v-if="tdTitle.hasOwnProperty('isReadOnly')"><dt>Read only:</dt><dd>&nbsp;{{ tdTitle.isReadOnly }}</dd></dl>
          <dl v-if="tdTitle.hasOwnProperty('defaultDisplay')"><dt>Display default:</dt><dd>&nbsp;{{ tdTitle.defaultDisplay }}</dd></dl>
          <dl v-if="tdTitle.hasOwnProperty('closeWindow')"><dt>Window close:</dt><dd>&nbsp;{{ tdTitle.closeWindow }}</dd></dl>
          <dl v-if="tdTitle.hasOwnProperty('nameOfClass')"><dt>Class:</dt><dd>&nbsp;{{ tdTitle.nameOfClass }}</dd></dl>
          <dl v-if="tdTitle.hasOwnProperty('nameOfMethod')"><dt>Method:</dt><dd>&nbsp;{{ tdTitle.nameOfMethod }}</dd></dl>
          <dl v-if="tdTitle.hasOwnProperty('secondaryAction')"><dt>Action:</dt><dd>&nbsp;{{ tdTitle.secondaryAction }}</dd></dl>
          <dl v-if="tdTitle.hasOwnProperty('customUrl')"><dt>Url:</dt><dd>&nbsp;{{ tdTitle.customUrl }}</dd></dl></div>
        </td>
      </tr>
      <tr v-for="trSubact in tbody.cRowSubact">
        <td v-for="tdSubact in trSubact.cellSubact" :colspan="tdSubact.colSpan" :class="{cellBorderRight: tdSubact.borderRight === true, cellBorderLeft: tdSubact.borderLeft === true, firstCell: tdSubact.isFirstCell === true}">
          <dl v-if="tdSubact.hasOwnProperty('actionName')"><dt>Subaction:</dt><dd>&nbsp;{{ tdSubact.actionName }}</dd></dl>
          <dl v-if="tdSubact.hasOwnProperty('processName')"><dt>Process:</dt><dd>&nbsp;{{ tdSubact.processName }}</dd></dl>
        </td>
      </tr>
      <tr v-for="trInclus in tbody.dRowInclus">
        <td v-for="tdInclus in trInclus.cellInclus" :colspan="tdInclus.colSpan" :class="{cellBorderRight: tdInclus.borderRight === true, cellBorderLeft: tdInclus.borderLeft === true, firstCell: tdInclus.isFirstCell === true}">
          <table 
          :ref='(tbl)=>{console.log("tbl");console.dir(tdInclus);let data=tdInclus.inclusion,col=["Transition","Inclusion","Exclusion"],thead=document.createElement("thead"),row,cell,txt,el;rowh=document.createElement("tr");for(let i=0;i<data.length;i++){if(data[i].inclusion===data[i].exclusion){alert("inclusion === exclusion for transition: "+data[i].actionName)}row=tbl.insertRow();for(let j=0;j<col.length;j++){cell=row.insertCell();cell.style.border="1px solid black";switch(j){case 0:txt=document.createTextNode(data[i].actionName);cell.appendChild(txt);break;case 1:el=document.createElement("input");el.type="radio";if(data[i].inclusion){el.setAttribute("checked","")}el.setAttribute("disabled","");cell.appendChild(el);break;case 2:el=document.createElement("input");el.type="radio";if(data[i].exclusion){el.setAttribute("checked","")}el.setAttribute("disabled","");cell.appendChild(el);break}}}row=window.parent.document.createElement("tr");thead.appendChild(row);for(let i=0;i<col.length;i++){cell=document.createElement("th");cell.style.border="1px solid black";txt=document.createTextNode(col[i]);cell.appendChild(txt);row.appendChild(cell)}tbl.insertBefore(thead,tbl.children[0])}'>
          </table>
        </td>
      </tr>
      <tr v-for="trAttr in tbody.cRowAttr">
        <td v-for="tdAttr in trAttr.cellAttr" :colspan="tdSubact.colSpan" :class="{cellBorderRight: tdAttr.borderRight === true, cellBorderLeft: tdAttr.borderLeft === true, firstCell: tdAttr.isFirstCell === true}">
          <dl v-if="tdAttr.hasOwnProperty('actionName')"><dt>Subaction:</dt><dd>&nbsp;{{ tdSubact.actionName }}</dd></dl>
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
                content: []
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
                console.log("request")
                console.dir(request)
                let templ = respStateTransitTempl(eTargetWoName.target.value, request);
                if (templ != null) {
                    this.data.content.push(templ)
                    console.log("templ")
                    console.dir(templ)
                }
            }
            request.send()
        },
        onUpdatedPreFormat: function() {
            if (this.data.content.length > 0) {
                // $(".firstCell").css({width: this.reference.tdState[0].td.offsetWidth / 2})
                $(".thState").css({width: 100 / $(".thState").length + '%'})
            }
        },
        onUpdatedHasArrow: function() {
            if (this.data.content.length > 0) {
                console.log("onUpdatedHasArrow this.reference.tdArrow " + this.reference.tdArrow);
                console.dir(this.reference)
                for (let arrow of this.reference.tdArrow) {
                    let direct = 0, pos = [];
                    if (arrow.stateTitle.length === 2 /*&& arrow[0] !== arrow[1]*/) {
                        direct = arrow.reverse === true ? -1 : 1
                    }
                    this.drawArrow(direct, arrow.td.firstChild)
                }
            }
        },
        drawArrow(direct, canvas) {
            let drawPlainArrow = function (beginX, endX, rowY, direct, canvas){
                console.log("drawArrow drawPlainArrow " + beginX + ' ' + endX + ' ' + rowY);
                console.dir(canvas);
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
            canvas.setAttribute("height",33)

            let beginX, endX, rowY = 21//canvas.clientHeight / 2;
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
            drawPlainArrow(beginX, endX, rowY, direct, canvas)
        }
    },
    beforeMount() {
        // console.log("beforeMount");
    },
    beforeUpdated() {
        // console.log("beforeUpdated");
    },
    updated() {
        console.log("updated");
        console.dir(this.data.content);
        this.onUpdatedHasArrow();
        this.onUpdatedPreFormat()
    },
    mounted() {
        // alert("mounted");
        // console.log("catalogSelected " + this.catalogSelected);
    }
}).mount('#app')
