package com.example.study.interpace;

import com.example.study.model.network.Header;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

import java.util.List;

public interface PagingInterface<Res> {

    Header<List<Res>> search(Pageable pageable);

}
