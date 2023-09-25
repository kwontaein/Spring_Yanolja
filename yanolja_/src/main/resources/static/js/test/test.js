// 모든 .calendar-day 클래스를 가진 요소를 선택하고 각각에 이벤트 리스너를 추가합니다.
document.querySelectorAll('.calendar-day').forEach(day => {
    day.addEventListener('click', () => {
        // 클릭한 날짜를 가져와 Date 객체로 변환합니다.
        const clickedDate = new Date(day.getAttribute('data-date'));

        if (!day.classList.contains('past-day') && clickedDate <= maxSelectableDate) {
            const maxSelectableEndDate = new Date(selectedStartDate);
            maxSelectableEndDate.setDate(selectedStartDate.getDate() + 9);

            if (!selectedStartDate || clickedDate < selectedStartDate) {
                // 시작 날짜가 선택되지 않았거나 클릭한 날짜가 시작 날짜보다 이전인 경우
                // 클릭한 날짜를 새로운 시작 날짜로 설정하고 이전의 시작 날짜를 지웁니다.
                document.querySelectorAll('.calendar-day').forEach(day => {
                    day.classList.remove('selected-start', 'selected-end', 'selected-between', 'selected-start-only', 'selected-end-only');
                });
                selectedStartDate = clickedDate;
                selectedEndDate = null;
                day.classList.add('selected-start'); // 시작 날짜 스타일

                updateDateRangeButtonText(selectedStartDate, selectedEndDate);

            } else if (!selectedEndDate && clickedDate <= maxSelectableEndDate) {
                // 종료 날짜가 선택되지 않았고, 클릭한 날짜가 시작 날짜보다 이후이며 9일 이내인 경우
                // 클릭한 날짜를 종료 날짜로 설정하고 중간의 날짜 스타일을 추가합니다.
                selectedEndDate = clickedDate;
                day.classList.add('selected-end'); // 종료 날짜 스타일

                // 중간에 있는 날짜들에 대한 스타일을 설정합니다.
                document.querySelectorAll('.calendar-day').forEach(day => {
                    const date = new Date(day.getAttribute('data-date'));
                    if (date > selectedStartDate && date < selectedEndDate) {
                        day.classList.add('selected-between'); // 중간 날짜 스타일
                    }
                });
            } else if (selectedStartDate && selectedEndDate && clickedDate !== selectedStartDate) {
                // 시작 날짜와 종료 날짜가 이미 선택되었고, 클릭한 날짜가 시작 날짜와 다를 때
                // 클릭한 날짜를 새로운 시작 날짜로 설정하고 종료 날짜를 초기화합니다.
                selectedStartDate = clickedDate;
                selectedEndDate = null;

                // 모든 날짜의 스타일을 초기화합니다.
                document.querySelectorAll('.calendar-day').forEach(day => {
                    day.classList.remove('selected-start', 'selected-end', 'selected-between', 'selected-start-only', 'selected-end-only');
                });

                day.classList.add('selected-start'); // 시작 날짜 스타일
            }

            // 시작 날짜만 선택된 경우 스타일 추가
            if (selectedStartDate && !selectedEndDate) {
                day.classList.add('selected-start-only'); // 시작 날짜만 선택된 경우 스타일
            }

            // 두 날짜가 모두 선택된 경우 시작 날짜만 스타일 제거
            if (selectedStartDate && selectedEndDate) {
                document.querySelectorAll('.calendar-day').forEach(day => {
                    day.classList.remove('selected-start-only');
                });
            }

            // 선택된 기간을 버튼에 표시
            if (selectedStartDate && selectedEndDate) {
                const formattedStartDate = selectedStartDate.toLocaleDateString();
                const formattedEndDate = selectedEndDate.toLocaleDateString();
                const timeDifference = selectedEndDate.getTime() - selectedStartDate.getTime();
                const daysDifference = Math.floor(timeDifference / (1000 * 60 * 60 * 24));
                dateRangeButton.textContent = `${formattedStartDate} - ${formattedEndDate} (${daysDifference}박)`;
            } else if (selectedStartDate) {
                const formattedStartDate = selectedStartDate.toLocaleDateString();
                dateRangeButton.textContent = `${formattedStartDate} 체크인 검색`;
            }
        }
    });
});
