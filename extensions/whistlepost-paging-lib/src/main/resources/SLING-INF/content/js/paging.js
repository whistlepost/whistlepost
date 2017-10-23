function calculateOffset(pageVal, data) {
    data.offset = 0;

	if (pageVal) {
		var range = pageVal.substring(1).split("-")
		if (range.length > 1) {
			data.offset = Math.min(parseInt(range[0]) * data.pageSize, data.total - 1);
			data.limit = Math.min(parseInt(range[1]) * data.pageSize + data.pageSize, data.total);
		} else {
			data.offset = Math.min(parseInt(range[0]) * data.pageSize, data.total - 1);
		}
	}
}

function calculatePageCount(data) {
	data.pageCount = Math.ceil(parseFloat(data.total) / data.pageSize);
}
