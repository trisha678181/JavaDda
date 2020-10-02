
function pweye(image) {
	
	/* 이미지 이름 가져오기 - eyeo.svg 나 eyex.svg */
	var name = image.src;
	name = name.substr(name.length-8, name.length);
	
	var img = $('#mp2eye');
	var input = $('#mp2i1');
	
	if(name == 'eyex.svg') { /* 비밀번호 안 보일 때 */
		
		/* eyeo.svg로 바꾸기 */
		img.attr('src', '../../../images/eyeo.svg');
		
		/* 비밀번호 보이게 하기 */
		input.attr('type', 'text');
		
		
	} else { /* 비밀번호 보일 때 */

		img.attr('src', '../../../images/eyex.svg');
	
		input.attr('type', 'password');

	}

}

function pweye2(image) {
	
	/* 이미지 이름 가져오기 - eyeo.svg 나 eyex.svg */
	var name = image.src;
	name = name.substr(name.length-8, name.length);
	
	
	var img2 = $('#mp2eye2');
	var input2 = $('#mp2i6');
	
if(name == 'eyex.svg') { /* 비밀번호 안 보일 때 */
		
		/* eyeo.svg로 바꾸기 */
		img2.attr('src', '../../../images/eyeo.svg');
		
		/* 비밀번호 보이게 하기 */
		input2.attr('type', 'text');
		
		
	} else { /* 비밀번호 보일 때 */

		img2.attr('src', '../../../images/eyex.svg');
	
		input2.attr('type', 'password');

	}
}

function pweye3(image) {
	
	/* 이미지 이름 가져오기 - eyeo.svg 나 eyex.svg */
	var name = image.src;
	name = name.substr(name.length-8, name.length);
	
var img3 = $('#mp2eye3');
var input3 = $('#mp2i7');
	
	if(name == 'eyex.svg') { /* 비밀번호 안 보일 때 */
		
		/* eyeo.svg로 바꾸기 */
		img3.attr('src', '../../../images/eyeo.svg');
		
		/* 비밀번호 보이게 하기 */
		input3.attr('type', 'text');
		
		
	} else { /* 비밀번호 보일 때 */

		img3.attr('src', '../../../images/eyex.svg');
	
		input3.attr('type', 'password');

	}
}