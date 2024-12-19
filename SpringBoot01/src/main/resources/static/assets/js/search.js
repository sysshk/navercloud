
// 검색하기

const input = document.getElementById("text");
const btn = document.getElementById("searchbtn");

btn.addEventListener("click", request)

function request() {

	// 1. url 준비
	let url = "search";
	// 1-1 데이터꺼내오기
	let text = input.value;
	// 1-2 쿼리 스트링으로 보내주기 / 데이터가 하나이므로 get 방식
	axios.get(url + "?text=" + text) // 1-3 boardRestController 로 요청을 보냄
		.then(function(res) { // controller 다녀와서 출력
			console.log(res.data);

			const tbody = document.querySelector("tbody");

			tbody.innerHTML = "";

			for (let i = 0; i < res.data.length; i++) {

				let board = res.data[i];

				tbody.innerHTML += `
					<tr>
						<td>${board.idx}</td>
						<td><a href="view?idx=${board.idx}">${board.title}</a></td>
						<td>${board.writer}</td>
						<td>${board.indate}</td>
						<td><a href="delete/${board.idx}">X</a></td>
					</tr>
				`;


			}

		})


}

// =====================================================
// chart 도전하기

let req = "chart";

axios.get(req)
	.then(function(res) {

		// 1. 배열로 전환하는 작업
		let writer = [];
		let count = [];

		for (let i = 0; i < res.data.length; i++) {
			writer.push(res.data[i].writer);
			count.push(res.data[i].count);
		}

		const ctx = document.getElementById('myChart');

		new Chart(ctx, {
			type: 'bar',
			data: {
				labels: writer,
				datasets: [{
					label: 'count',
					data: count,
					borderWidth: 1
				}]
			},
			options: {
				scales: {
					y: {
						beginAtZero: true
					}
				}
			}
		});


	});






