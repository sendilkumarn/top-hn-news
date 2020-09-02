//
//  NewsRow.swift
//  iosApp
//
//  Created by Sendil Kumar N on 2/9/20.
//  Copyright © 2020 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NewsItemRow: View {
    var newsItem: NewsItem
    
    var body: some View {
        HStack() {
            Button(action: {
                guard let url = URL(string: self.newsItem.url) else { return }
                UIApplication.shared.open(url)
            }) {
                VStack(alignment: .leading, spacing: 10.0) {
                    Text(newsItem.title)
                        .fontWeight(.bold)
                        .font(.system(.subheadline, design: .rounded))
                    Text(newsItem.author)
                        .font(.caption)
                }
                Spacer()
            }
        }
    }
}

