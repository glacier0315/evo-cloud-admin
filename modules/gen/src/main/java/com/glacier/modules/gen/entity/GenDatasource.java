/*
 * Copyright (c) 2020 pig4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.glacier.modules.gen.entity;

import com.glacier.common.core.entity.AbstractDataEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 数据源表  模型层
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 21:53
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class GenDatasource extends AbstractDataEntity {
	private static final long serialVersionUID = -3256542275939203325L;
	/**
	 * 名称
	 */
	private String name;

	/**
	 * jdbc url
	 */
	private String url;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

}
