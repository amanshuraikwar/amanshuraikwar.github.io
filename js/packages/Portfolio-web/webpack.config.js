let config = {
  mode: 'production',
  resolve: {
    modules: [
      "node_modules"
    ]
  },
  plugins: [],
  module: {
    rules: []
  }
};

// entry
config.entry = {
    main: ["/Users/runner/work/amanshuraikwar.github.io/amanshuraikwar.github.io/project-files/Portfolio/build/js/packages/Portfolio-web/kotlin/Portfolio-web.js"]
};

config.output = {
    path: "/Users/runner/work/amanshuraikwar.github.io/amanshuraikwar.github.io/project-files/Portfolio/web/build/distributions",
    filename: (chunkData) => {
        return chunkData.chunk.name === 'main'
            ? "web.js"
            : "web-[name].js";
    },
    library: "web",
    libraryTarget: "umd",
    globalObject: "this"
};

// source maps
config.module.rules.push({
        test: /\.js$/,
        use: ["source-map-loader"],
        enforce: "pre"
});
config.devtool = 'source-map';
config.ignoreWarnings = [/Failed to parse source map/]

// Report progress to console
// noinspection JSUnnecessarySemicolon
;(function(config) {
    const webpack = require('webpack');
    const handler = (percentage, message, ...args) => {
        const p = percentage * 100;
        let msg = `${Math.trunc(p / 10)}${Math.trunc(p % 10)}% ${message} ${args.join(' ')}`;
        msg = msg.replace("/Users/runner/work/amanshuraikwar.github.io/amanshuraikwar.github.io/project-files/Portfolio/build/js", '');;
        console.log(msg);
    };

    config.plugins.push(new webpack.ProgressPlugin(handler))
})(config);

// noinspection JSUnnecessarySemicolon
;(function(config) {
    const tcErrorPlugin = require('kotlin-test-js-runner/tc-log-error-webpack');
    config.plugins.push(new tcErrorPlugin())
    config.stats = config.stats || {}
    Object.assign(config.stats, config.stats, {
        warnings: false,
        errors: false
    })
})(config);
// save evaluated config file
;(function(config) {
    const util = require('util');
    const fs = require('fs');
    const evaluatedConfig = util.inspect(config, {showHidden: false, depth: null, compact: false});
    fs.writeFile("/Users/runner/work/amanshuraikwar.github.io/amanshuraikwar.github.io/project-files/Portfolio/web/build/reports/webpack/Portfolio-web/webpack.config.evaluated.js", evaluatedConfig, function (err) {});
})(config);

module.exports = config
