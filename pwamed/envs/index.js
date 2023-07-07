function get(env, stringify = false) {
  const IS_DEV = env === "dev" || env === "local";
  const IS_LOCAL = env === "local";
  const IS_TEST = env === "test";
  const IS_TEST_TOK = env === "test-tok";
  const IS_PROD = env === "prod";
  const IS_PROD_TOK = env === "prod-tok";

  let envs = {};
  if (IS_DEV) envs = require("./dev.env");
  if (IS_LOCAL) envs = require("./local.env");
  if (IS_TEST) envs = require("./test.env");
  if (IS_TEST_TOK) envs = require("./test-tok.env");
  if (IS_PROD) envs = require("./prod.env");
  if (IS_PROD_TOK) envs = require("./prod-tok.env");

  envs.APP_ENV = env;
  envs.APP_IS_DEV = IS_DEV;
  envs.APP_IS_TEST = IS_TEST;
  envs.APP_IS_PROD = IS_PROD;
  envs.APP_IS_TEST_TOK = IS_TEST_TOK;
  envs.APP_IS_PROD_TOK = IS_PROD_TOK;

  if (!stringify) return envs;
  return Object.keys(envs).reduce((out, k) => {
    out[k] = JSON.stringify(envs[k]);
    return out;
  }, {});
}

module.exports = { get };
