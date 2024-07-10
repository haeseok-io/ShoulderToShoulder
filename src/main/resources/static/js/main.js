$(() => {});

// 직무 중분류 가져오기
const getPositionDetail = (positionTarget, positionDetailTarget, placeholder) => {
    let positionNo = positionTarget.find("option:selected").val();

    // 기존 중분류 옵션 제거
    positionDetailTarget.find("option").remove();

    // 선택값 없음에 대한 옵션 추가
    if( placeholder )   positionDetailTarget.append(`<option value="">${placeholder}</option>`);

    // 넘어온 대분류값이 있을경우에만 처리
    if( positionNo ) {
        $.get(`/position/${positionNo}/details`, data => {
            data.forEach(detail => {
                positionDetailTarget.append(`<option value="${detail.no}">${detail.name}</option>`)
            });

            // 콜백 처리
            if( typeof getPositionDetailCallback==="function" ) {
                getPositionDetailCallback(positionDetailTarget);
            }
        }, "json");
    }
}


// multiple select 선택 옵션 가져오기
const getMultipleValues = name => {
    let valueArray = [];
    let element = $(`[name='${name}']`);

    if( element.get(0).localName==="select" ) {
        element.find("option").each((idx, el) => {
            if( $(el).is(":selected") ) {
                valueArray.push($(el).val());
            }
        });
    } else {
        let elementType = element.attr("type");

        if( elementType==='checkbox' ) {
            element.each((idx, el) => {
                if( $(el).is(":checked") ) {
                    valueArray.push($(el).val());
                }
            });
        } else {
            element.each((idx, el) => {
                valueArray.push($(el).val());
            });
        }
    }

    return valueArray;
}