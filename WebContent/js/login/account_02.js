(function (member, $) {

    member.join = member.join || {};

    member.join.agree = {
        $agreeResult: $("#agreeResult"),
        $agreeMail: $("#sendEmail"),
        $optional: $("#optional"),
        $frmSubJoinSimple: $("#frmSubJoinSimple"),
        $btnNext: $('.btn_agree'),
        $totalCheck: $('.policy_check').find('input#totalCheck'),
        $termsCheck: $('.policy_check').find('input#termsCheck'),
        $termsAlert: $('.policy_check').find('input#termsCheck').closest('.box_check_policy').find('.text_alert'),
        $infoCheck: $('.policy_check').find('input#infoCheck'),
        $infoAlert: $('.policy_check').find('input#infoCheck').closest('.box_check_policy').find('.text_alert'),
        $infoAddCheck: $('.policy_check').find('input#infoAddCheck'),
        $mailingCheck: $('.policy_check').find('input#mailingCheck'),
        $mailingAlert: $('.policy_check').find('input#mailingCheck').closest('.box_check_policy').find('.text_alert'),

        /** 약관 [전체보기] 버튼 **/
        $termsCheckAllViewButton: $('.check_terms').find('a.btn_all'),
        $infoCheckAllViewButton: $('.check_info').find('a.btn_all'),
        $infoAddCheckAllViewButton: $('.check_infoadd').find('a.btn_all'),

        /** 전체보기 레이어 **/
        $layerTermsCheck: $('.promise_layer.termscheck'),
        $layerInfoCheck: $('.promise_layer.infocheck'),
        $layerInfoAddCheck: $('.promise_layer.infoaddcheck'),
        $promiseLayer: $('.promise_layer'),

        /** 전체보기 레이어 [동의하기] 버튼 **/
        $termsCheckAllViewAgreeButton: $('.promise_layer.termscheck .agree'),
        $infoCheckAllViewAgreeButton: $('.promise_layer.infocheck .agree'),
        $infoAddCheckAllViewAgreeButton: $('.promise_layer.infoaddcheck .agree'),

        /** 전체보기 레이어 [닫기] 버튼 **/
        $allViewCloseButton: $('.promise_layer .btn_close'),

        init: function () {

            var _ = member.join.agree;

            _.$agreeResult.val("false");
            _.$agreeMail.val("false");
            _.$optional.val("false");
            _.$termsCheck.prop('checked', false);
            _.$infoCheck.prop('checked', false);
            _.$infoAddCheck.prop('checked', false);
            _.$mailingCheck.prop('checked', false);

            _.$btnNext.click(_.next.click);
            _.$totalCheck.change(_.checkTotal);
            _.$termsCheck.change(function () {
                if (_.$termsCheck.prop('checked'))
                    _.termsOn();
                else
                    _.termsOff();
            });
            _.$infoCheck.change(function () {
                if (_.$infoCheck.prop('checked'))
                    _.infoOn();
                else
                    _.infoOff();
            });
            _.$infoAddCheck.change(function () {
                if (_.$infoAddCheck.prop('checked'))
                    _.infoAddOn();
                else
                    _.infoAddOff();
            });
            _.$mailingCheck.change(function () {
                if (_.$mailingCheck.prop('checked'))
                    _.mailOn();
                else
                    _.mailOff();
            });


            /** 약관 [전체보기] 버튼 이벤트 등록 **/
            _.$termsCheckAllViewButton.on('click', function () {
                _.promiseLayerShow(_.$layerTermsCheck);
            });

            _.$infoCheckAllViewButton.on('click', function () {
                _.promiseLayerShow(_.$layerInfoCheck);
            });

            _.$infoAddCheckAllViewButton.on('click', function () {
                _.promiseLayerShow(_.$layerInfoAddCheck);
            });


            /** 약관 [전체보기] 버튼 이후 [동의하기] 버튼 이벤트 등록 **/
            _.$termsCheckAllViewAgreeButton.on('click', function () {
                _.promiseLayerHide(_.$promiseLayer);
                _.termsOn();
            });

            _.$infoCheckAllViewAgreeButton.on('click', function () {
                _.promiseLayerHide(_.$promiseLayer);
                _.infoOn();
            });

            _.$infoAddCheckAllViewAgreeButton.on('click', function () {
                _.promiseLayerHide(_.$promiseLayer);
                _.infoAddOn();
            });

            /** 전체보기 레이어 [닫기] 버튼 이벤트 등록 **/
            _.$allViewCloseButton.on('click', function () {
                _.promiseLayerHide(_.$promiseLayer);
            });

        },

        checkTotal: function () {
            var _ = member.join.agree;
            _.$totalCheck.next('label').toggleClass('checked');
            if (_.$totalCheck.prop('checked')) {
                _.onAll();
            } else {
                _.offAll();
            }
        },

        onAll: function () {
            var _ = member.join.agree;

            _.termsOn();
            _.infoOn();
            _.infoAddOn();
            _.mailOn();

            _.$agreeResult.val("true");
        },

        offAll: function () {
            var _ = member.join.agree;

            _.termsOff();
            _.infoOff();
            _.infoAddOff();
            _.mailOff();

            _.$agreeResult.val("false");
        },

        termsOn: function () {
            var _ = member.join.agree;
            _.on(_.$termsCheck);
            _.$termsAlert.hide();
        },

        termsOff: function () {
            var _ = member.join.agree;
            _.off(_.$termsCheck);
            _.$termsAlert.show();
        },

        infoOn: function () {
            var _ = member.join.agree;
            _.on(_.$infoCheck);
            _.$infoAlert.hide();
        },

        infoOff: function () {
            var _ = member.join.agree;
            _.off(_.$infoCheck);
            _.$infoAlert.show();
        },

        infoAddOn: function () {
            var _ = member.join.agree;
            _.on(_.$infoAddCheck);
            _.$optional.val("true");
        },

        infoAddOff: function () {
            var _ = member.join.agree;
            _.off(_.$infoAddCheck);
            _.$optional.val("false");
        },

        mailOn: function () {
            var _ = member.join.agree,
                date = new Date(),
                month = date.getMonth() + 1,
                day = date.getDate();

            _.on(_.$mailingCheck);
            _.$mailingAlert.find('p').text(month + '월 ' + day + '일 현재 프로모션 안내 메일 수신에 동의하셨습니다.');
            _.$mailingAlert.show();
            _.$agreeMail.val("true");
        },

        mailOff: function () {
            var _ = member.join.agree;
            _.off(_.$mailingCheck);
            _.$mailingAlert.hide();
            _.$agreeMail.val("false");
        },

        on: function (target) {
            var _ = member.join.agree;
            target.prop('checked', true);
            target.next('label').addClass('checked');
            _.checkAll();
        },

        off: function (target) {
            var _ = member.join.agree;
            target.prop('checked', false);
            target.next('label').removeClass('checked');
            _.checkAll();
        },

        checkAll: function () {
            var _ = member.join.agree;
            var checkLists = [_.$termsCheck, _.$infoCheck, _.$infoAddCheck, _.$mailingCheck];
            var result = true;

            for (var i = 0; i < checkLists.length; ++i)
                result = result && checkLists[i].prop('checked');

            if (result)
                _.onTotal();
            else
                _.offTotal();
        },

        onTotal: function () {
            var _ = member.join.agree;
            _.$totalCheck.next('label').addClass('checked');
            _.$totalCheck.prop('checked', true);
        },

        offTotal: function () {
            var _ = member.join.agree;
            _.$totalCheck.next('label').removeClass('checked');
            _.$totalCheck.prop('checked', false);
        },

        promiseLayerShow: function(target){
            target.show();
        },

        promiseLayerHide: function(target){
            target.hide();
        },

        next: {
            click: function () {
                var _ = member.join.agree;
                if (!(_.$termsCheck.is(":checked"))) {
                    _.termsOff();
                    return false;
                }
                if (!(_.$infoCheck.is(":checked"))) {
                    _.infoOff();
                    return false;
                }

                _.$agreeResult.val("true");
                _.$frmSubJoinSimple.submit();
                return false;
            }
        }
    };

    member.join.agree.init();

})(window["member"] = window["member"] || {}, jQuery);