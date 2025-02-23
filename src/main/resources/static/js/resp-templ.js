var respStateTransitTempl = function (woName, request) {
    let buildTempl = function(name, data) {
        let calcTransitState = function(state, transit) {
            let result = {stateStepN: []};
            for (let j = 0; j < state.length; j++) {
                if (transit.currentState == state[j] || transit.nextState == state[j]) {
                    result.stateStepN.push(j);
                    if (transit.currentState == transit.nextState) {
                        break
                    }
                    if (result.stateStepN.length === 2) {
                        if (transit.currentState == state[j]) {
                            result.stateStepR = true;
                        }
                        break
                    }
                }
            }
            return result
        }
        let rowArrowBuild = function(state, transit, cellArrow) {
            let addArrowFirst = function(cell) {
                if (cell.length === 0) {
                    cell.push({borderRight:true, isFirstCell:true})
                }
            }
            let addArrow = function(state, cell, param, flag) {
                if (cell.length !== 0) {
                    let arrowResult, colSpanResult;
                    if (param.stateStepN.length === 1) {
                        arrowResult = [state[param.stateStepN[0]]]
                    } else {
                        arrowResult = [state[param.stateStepN[0]], state[param.stateStepN[1]]]
                    }
                    if (param.stateStepN.length === 1) {
                        colSpanResult = 2
                    } else {
                        colSpanResult = (param.stateStepN[1] - param.stateStepN[0]) * 2
                    }
                    cell.push({arrow: arrowResult, colSpan: colSpanResult, borderLeft: true, borderRight: true, canvas: true} );
                    if (param.hasOwnProperty("stateStepR")) {
                        cell[cell.length - 1].reverse = true
                    }
                    flag[0] = false
                }
            }
            let addArrowLeft = function(cell, flag) {
                cell.push({borderLeft:true});
                flag[0] = true
            }
            let addArrowRight = function(cell, flag) {
                cell.push({borderRight:true});
                flag[0] = false
            }
            if (state.length > 0) {
                if (state.length === 1) { // ? 1 cell
                    cBodyResult.rowArrow.push(JSON.parse('{"borderRight":true, "isFirstCell":true}, {"arrow":["null"], "colSpan": 2, "borderLeft":true, "borderRight":true, "canvas":true}'))
                } else {
                    let arrowRowParam = calcTransitState(state, transit),
                        right = [false];
                    for (let k = 0; k < (state.length * 2 - (arrowRowParam.stateStepN.length === 1 ? (1 * 2 - 1) : (arrowRowParam.stateStepN[1] - arrowRowParam.stateStepN[0]) * 2 - 1)); k++) {
                        if (k === 0) {
                            addArrowFirst(cellArrow)
                        } else {
                            if ( (arrowRowParam.stateStepN[0] * 2 + 1) === k ) {
                                addArrow(state, cellArrow, arrowRowParam, right)
                            } else {
                                if (right[0] === true) {
                                    addArrowRight(cellArrow, right)
                                } else {
                                    addArrowLeft(cellArrow, right)
                                }
                            }
                        }
                    }
                }
            }
        }
        let rowTransitBuild = function(state, transit, cellTransit) {
            let addTransitFirst = function(cell) {
                if (cell.length === 0) {
                    cell.push({borderRight:true, isFirstCell:true})
                }
            }
            let addTransit = function(cell, transit) {
                if (cell.length !== 0) {
                    cell.push({
                        label: transit.actionLabel,
                        name: transit.actionName,
                        currentState: transit.currentState,
                        nextState: transit.nextState,
                        customUrl: transit.customUrl,
                        nameOfClass: transit.nameOfClass,
                        nameOfMethod: transit.nameOfMethod,
                        defaultDisplay: transit.defaultDisplay,
                        isReadOnly: transit.isReadOnly,
                        closeWindow: transit.closeWindow,
                        secondaryAction: transit.secondaryAction,
                        colSpan: 2, borderLeft: true, borderRight: true })
                }
            }
            let addTransitLeft = function(cell, flag) {
                cell.push({borderLeft:true});
                flag[0] = true
            }
            let addTransitRight = function(cell, flag) {
                cell.push({borderRight:true});
                flag[0] = false
            }
            if (state.length > 0) {
                if (state.length === 1) { // ? 1 cell
                    cBodyResult.rowTransit.push(JSON.parse('{"borderRight":true, "isFirstCell":true}, {"label": ' + transit.actionLabel
                        + ', "name": ' + transit.actionName
                        + ', currentState: ' + transit.currentState
                        + ', nextState: ' + transit.nextState
                        + ', customUrl: ' + transit.customUrl
                        + ', nameOfClass: ' + transit.nameOfClass
                        + ', nameOfMethod: ' + transit.nameOfmethod
                        + ', defaultDisplay: ' + transit.defaultDisplay
                        + ', isReadOnly: ' + transit.isReadOnly
                        + ', closeWindow: ' + transit.closeWindow
                        + ', secondaryAction: ' + transit.secondaryAction
                        + ', "colSpan": 2, "borderLeft":true, "borderRight":true}'))
                } else {
                    let transitRowParam = calcTransitState(state, transit),
                        right = [false];
                    for (let k = 0; k < (state.length * 2 - (transitRowParam.stateStepN.length === 1 ? (1 * 2 - 1) : (transitRowParam.stateStepN[1] - transitRowParam.stateStepN[0]) * 2 - 1)); k++) {
                        if (k === 0) {
                            addTransitFirst(cellTransit)
                        } else {
                            if ( (transitRowParam.stateStepN[0] * 2 + 1) === k ) {
                                addTransit(cellTransit, transit)
                            } else {
                                if (right[0] === true) {
                                    addTransitRight(cellTransit, right)
                                } else {
                                    addTransitLeft(cellTransit, right)
                                }
                            }
                        }
                    }
                }
            }
        }
        let rowSubactBuild = function(state, transitStruct, cellSubact) {
            let addSubactFirst = function(cell) {
                if (cell.length === 0) {
                    cell.push({borderRight:true, isFirstCell:true})
                }
            }
            let addSubact = function(cell, transitStruct) {
                if (cell.length !== 0) {
                    cell.push( {actionName: transitStruct.subaction[0].actionName, processName: transitStruct.subaction[0].processName, colSpan: 2, borderLeft: true, borderRight: true} );
                }
            }
            let addSubactLeft = function(cell, flag) {
                cell.push({borderLeft:true});
                flag[0] = true
            }
            let addSubactRight = function(cell, flag) {
                cell.push({borderRight:true});
                flag[0] = false
            }
            if (state.length > 0 && transitStruct.hasOwnProperty("subaction") && transitStruct.subaction.length > 0) {
                if (state.length === 1) { // ? 1 cell
                    cBodyResult.rowTransit.push(JSON.parse('{"borderRight":true, "isFirstCell":true}, {"actionName": ' + transitStruct.subaction[0].actionName + ', "processName": ' + transitStruct.subaction[0].processName + ', "colSpan": 2, "borderLeft":true, "borderRight":true}'))
                } else {
                    let transitRowParam = calcTransitState(state, transitStruct),
                        right = [false];
                    for (let k = 0; k < (state.length * 2 - (transitRowParam.stateStepN.length === 1 ? (1 * 2 - 1) : (transitRowParam.stateStepN[1] - transitRowParam.stateStepN[0]) * 2 - 1)); k++) {
                        if (k === 0) {
                            addSubactFirst(cellSubact)
                        } else {
                            if ( (transitRowParam.stateStepN[0] * 2 + 1) === k ) {
                                addSubact(cellSubact, transitStruct)
                            } else {
                                if (right[0] === true) {
                                    addSubactRight(cellSubact, right)
                                } else {
                                    addSubactLeft(cellSubact, right)
                                }
                            }
                        }
                    }
                }
            }
        }
        let rowInclBuild = function(state, transitStruct, cellIncl) {
            let addInclFirst = function(cell) {
                if (cell.length === 0) {
                    cell.push({borderRight:true, isFirstCell:true})
                }
            }
            let addInclus = function(cell, transitStruct) {
                let inclusionTemp = [];
                if (cell.length !== 0) {
                    for (const inclusionCurrent of transitStruct.subaction[0].inclusion) {
                        inclusionTemp.push({actionName: inclusionCurrent.actionName,
                            inclusion: inclusionCurrent.inclusion,
                            exclusion: inclusionCurrent.exclusion})
                    }
                    cell.push({inclusion: inclusionTemp, colSpan: 2, borderLeft: true, borderRight: true })
                }
            }
            let addInclLeft = function(cell, flag) {
                cell.push({borderLeft:true});
                flag[0] = true
            }
            let addInclRight = function(cell, flag) {
                cell.push({borderRight:true});
                flag[0] = false
            }
            if (state.length > 0 && transitStruct.hasOwnProperty("subaction") && transitStruct.subaction.length === 1
                && transitStruct.subaction[0].hasOwnProperty("inclusion") && transitStruct.subaction[0].inclusion.length > 0) {
                if (state.length === 1) { // ? 1 cell
                    cBodyResult.rowTransit.push(
                        JSON.parse(
                            '{"borderRight":true, "isFirstCell":true}, {"inclusion": [], "colSpan": 2, "borderLeft":true, "borderRight":true}' ) )
                    for (const inclusionCurrent of transitStruct.subaction[0].inclusion) {
                        cBodyResult.rowTransit[1].inclusion.push('{"actionName": ' + inclusionCurrent.actionName
                            + ', "inclusion": ' + inclusionCurrent.inclusion
                            + ', "exclusion": ' + inclusionCurrent.exclusion + '}')
                    }
                } else {
                    let transitRowParam = calcTransitState(state, transitStruct),
                        right = [false];
                    for (let k = 0; k < (state.length * 2 - (transitRowParam.stateStepN.length === 1 ? (1 * 2 - 1) : (transitRowParam.stateStepN[1] - transitRowParam.stateStepN[0]) * 2 - 1)); k++) {
                        if (k === 0) {
                            addInclFirst(cellIncl)
                        } else {
                            if ( (transitRowParam.stateStepN[0] * 2 + 1) === k ) {
                                addInclus(cellIncl, transitStruct)
                            } else {
                                if (right[0] === true) {
                                    addInclRight(cellIncl, right)
                                } else {
                                    addInclLeft(cellIncl, right)
                                }
                            }
                        }
                    }
                }
            }
        }
        let rowAttrBuild = function(state, transitStruct, cellAttr) {
            let addAttrFirst = function(cell) {
                if (cell.length === 0) {
                    cell.push({borderRight:true, isFirstCell:true})
                }
            }
            let addAttr = function(cell, transitStruct) {
                let attributeTemp = [];
                if (cell.length !== 0) {
                    for (const attributeCurrent of transitStruct.subaction[0].attribute) {
                        attributeTemp.push({fieldName: attributeCurrent.fieldName,
                            constValue: attributeCurrent.constValue})
                    }
                    cell.push( {attribute: attributeTemp, colSpan: 2, borderLeft: true, borderRight: true} )
                }
            }
            let addAttrLeft = function(cell, flag) {
                cell.push({borderLeft:true});
                flag[0] = true
            }
            let addAttrRight = function(cell, flag) {
                cell.push({borderRight:true});
                flag[0] = false
            }
            if (state.length > 0 && transitStruct.hasOwnProperty("subaction") && transitStruct.subaction.length > 0
                && transitStruct.subaction[0].hasOwnProperty("attribute") && transitStruct.subaction[0].attribute.length > 0) {
                if (state.length === 1) { // ? 1 cell
                    cBodyResult.rowTransit.push(
                        JSON.parse(
                            '{"borderRight":true, "isFirstCell":true}, {"attribute": [], "colSpan": 2, "borderLeft":true, "borderRight":true}' ) );
                    for (const attributeCurrent of transitStruct.subaction[0].attribute) {
                        cBodyResult.rowTransit[1].attribute.push('{"fieldName": ' + attributeCurrent.fieldName
                            + ', "constValue": ' + attributeCurrent.constValue + '}')
                    }
                } else {
                    let transitRowParam = calcTransitState(state, transitStruct),
                        right = [false];
                    for (let k = 0; k < (state.length * 2 - (transitRowParam.stateStepN.length === 1 ? (1 * 2 - 1) : (transitRowParam.stateStepN[1] - transitRowParam.stateStepN[0]) * 2 - 1)); k++) {
                        if (k === 0) {
                            addAttrFirst(cellAttr)
                        } else {
                            if ( (transitRowParam.stateStepN[0] * 2 + 1) === k ) {
                                addAttr(cellAttr, transitStruct)
                            } else {
                                if (right[0] === true) {
                                    addAttrRight(cellAttr, right)
                                } else {
                                    addAttrLeft(cellAttr, right)
                                }
                            }
                        }
                    }
                }
            }
        }
        let templContentObj;
        if (data.hasOwnProperty(name)) {
            let objReceive = data[name];
            if (objReceive.length === 2 && objReceive[0].hasOwnProperty("state")) {
                templContentObj = JSON.parse('{"aCaption":null,"bHead":[{"aRowh":[{"column":[]}],"bRowd":[{"state":[]}]}],"cBody":[]}');
                templContentObj.aCaption = "State transition mapping for work object : " + name;
                for (let i = 0; i < objReceive[0].state.length * 2; i++) {
                    templContentObj.bHead[0].aRowh[0].column.push({})
                }
                for (let i = 0; i < objReceive[0].state.length; i++) {
                    templContentObj.bHead[0].bRowd[0].state.push({title: objReceive[0].state[i], colSpan: 2})
                }
                for (let i = 0; i < objReceive[1].transit.length; i++) {
                    templContentObj.cBody.push(JSON.parse('{"aRowArrow":[{"cellArrow":[]}],"bRowTransit":[{"cellTransit":[]}],"cRowSubact":[{"cellSubact":[]}],"dRowInclus":[{"cellInclus":[]}],"eRowAttr":[{"cellAttr":[]}]}'));
                    rowArrowBuild(objReceive[0].state, objReceive[1].transit[i], templContentObj.cBody[i].aRowArrow[0].cellArrow);
                    rowTransitBuild(objReceive[0].state, objReceive[1].transit[i], templContentObj.cBody[i].bRowTransit[0].cellTransit);
                    rowSubactBuild(objReceive[0].state, objReceive[1].transit[i], templContentObj.cBody[i].cRowSubact[0].cellSubact);
                    rowInclBuild(objReceive[0].state, objReceive[1].transit[i], templContentObj.cBody[i].dRowInclus[0].cellInclus);
                    rowAttrBuild(objReceive[0].state, objReceive[1].transit[i], templContentObj.cBody[i].eRowAttr[0].cellAttr)
                }
            } else {
                alert("alert content data")
            }
        } else {
            alert("alert content");
        }
        return templContentObj
    }
    return buildTempl(woName, JSON.parse(request.response))
}
