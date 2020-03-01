package com.barreto.android.presentation.content

import com.barreto.android.data.content.ContentRepository
import com.barreto.android.data.content.local.ContentLocalData
import com.barreto.android.data.content.local.IContentLocalData
import com.barreto.android.data.content.remote.ContentRemoteData
import com.barreto.android.data.content.remote.IContentRemoteData
import com.barreto.android.data.content.remote.api.IContentApiClient
import com.barreto.android.data.provider.RemoteClientFactory
import com.barreto.android.di.REMOTE_CLIENT_FACTORY
import com.barreto.android.domain.content.IContentRepository
import com.barreto.android.domain.content.usecase.GetContentListUseCase
import org.koin.android.experimental.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.experimental.builder.factory
import org.koin.experimental.builder.factoryBy
import org.koin.experimental.builder.singleBy

val contentModule = module {

    viewModel<ContentViewModel>()

    factory<GetContentListUseCase>()

    singleBy<IContentRepository, ContentRepository>()

    factoryBy<IContentLocalData, ContentLocalData>()

    factoryBy<IContentRemoteData, ContentRemoteData>()

    single { get<RemoteClientFactory>(named(REMOTE_CLIENT_FACTORY)).createClient(IContentApiClient::class) }
}